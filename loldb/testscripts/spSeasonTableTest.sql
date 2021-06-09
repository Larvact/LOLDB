USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSeasonTableTest] (@ExpectedNumberOfSeasons SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of seasons have been added to the season table*/
		DECLARE @ActualNumberOfSeasons SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the season table is ' + CAST(@ExpectedNumberOfSeasons AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of teams within the season table is ' + CAST(@ExpectedNumberOfSeasons AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfSeasons = (SELECT COUNT(s.Id) FROM [dbo].[Season] s)
			SET @ActualResult = 'The total number of seasons within the season table is ' + CAST(@ActualNumberOfSeasons AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfSeasons = @ActualNumberOfSeasons
			BEGIN
				SET @TestOutcome = 1;
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
			END
		ELSE
			BEGIN
				SET @TestOutcome = 0;
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
			END
	END