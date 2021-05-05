USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spChampionRoleTableTest] (@ExpectedNumberOfChampionRoleCombinations SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of champion - role combinations have been added to the championrole table*/
		DECLARE @ActualNumberOfChampionRoleCombinations SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the championrole table is ' + CAST(@ExpectedNumberOfChampionRoleCombinations AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of rows within the championrole table is ' + CAST(@ExpectedNumberOfChampionRoleCombinations AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfChampionRoleCombinations = (SELECT COUNT(*) FROM [dbo].[ChampionRole] cr)
			SET @ActualResult = 'The total number of rows within the championrole table is ' + CAST(@ActualNumberOfChampionRoleCombinations AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfChampionRoleCombinations = @ActualNumberOfChampionRoleCombinations
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