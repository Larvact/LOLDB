USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spTeamTableTest] (@ExpectedNumberOfTeams SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of teams have been added to the team table*/
		DECLARE @ActualNumberOfTeams SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the team table is ' + CAST(@ExpectedNumberOfTeams AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of teams within the team table is ' + CAST(@ExpectedNumberOfTeams AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfTeams = (SELECT COUNT(t.Id) FROM [dbo].[Team] t)
			SET @ActualResult = 'The total number of teams within the team table is ' + CAST(@ActualNumberOfTeams AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfTeams = @ActualNumberOfTeams
			BEGIN
				SET @TestOutcome = 1;
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
				SET @TestOutcome = 0;
			END
		ELSE
			BEGIN
				SET @TestOutcome = 0;
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
			END
	END