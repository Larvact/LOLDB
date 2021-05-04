USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSpecificChampionTest] (@SelectedChampion VARCHAR(32))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of champions have been added to the champions table*/
		SET @TestDescription = 'Ensure that the champion ' + @SelectedChampion + ' exits within the champion table';
		SET @ExpectedResult = @SelectedChampion + ' exists within the champion table';

		BEGIN TRY
			IF Exists
			(SELECT * FROM [dbo].[Champion] c
				WHERE c.Name = @SelectedChampion
			)
				BEGIN
					SET @ActualResult = @SelectedChampion + ' exists within the champion table';
					SET @TestOutcome = 1;
				END
			ELSE
				BEGIN
					SET @ActualResult = @SelectedChampion + ' does not exist within the champion table';
					SET @TestOutcome = 0;
				END
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
			SET @TestOutcome = 0;
		END CATCH

		INSERT INTO [test].[TestResults]
		VALUES (@TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
				
	END