USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spChampionTableTest] (@ExpectedNumberOfChampions SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of champions have been added to the champions table*/
		DECLARE @ActualNumberOfChampions SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the table is ' + CAST(@ExpectedNumberOfChampions AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of champions within the champions table is ' + CAST(@ExpectedNumberOfChampions AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfChampions = (SELECT COUNT(c.Id) FROM [dbo].[Champion] c)
			SET @ActualResult = 'The total number of champions within the champions table is ' + CAST(@ActualNumberOfChampions AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfChampions = @ActualNumberOfChampions
			BEGIN
				SET @TestOutcome = 1;
				INSERT INTO [test].[TestResults]
				VALUES (@TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
				SET @TestOutcome = 0;
			END
		ELSE
			BEGIN
				SET @TestOutcome = 0;
				INSERT INTO [test].[TestResults]
				VALUES (@TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
			END
	END