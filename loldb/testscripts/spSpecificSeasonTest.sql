USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSpecificSeasonTest] (@SelectedSeason VARCHAR(16))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the selected Season exists within the season table*/
		SET @TestDescription = 'Ensure that the season ' + @SelectedSeason + ' exits within the season table';
		SET @ExpectedResult = @SelectedSeason + ' exists within the season table';

		BEGIN TRY
			IF Exists
			(SELECT 1 FROM [dbo].[Season] s
				WHERE s.Name = @SelectedSeason
			)
				BEGIN
					SET @ActualResult = @SelectedSeason + ' exists within the season table';
					SET @TestOutcome = 1;
				END
			ELSE
				BEGIN
					SET @ActualResult = @SelectedSeason + ' does not exist within the season table';
					SET @TestOutcome = 0;
				END
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
			SET @TestOutcome = 0;
		END CATCH

		INSERT INTO [test].[TestResults]
		VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
	END