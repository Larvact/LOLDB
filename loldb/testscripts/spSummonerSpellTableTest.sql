USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSummonerSpellTableTest] (@ExpectedNumberOfSummonerSpells SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of summoner spells have been added to the SummonerSpell table*/
		DECLARE @ActualNumberOfSummonerSpells SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the summonerspell table is ' + CAST(@ExpectedNumberOfSummonerSpells AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of summoner spells within the summonerspell table is ' + CAST(@ExpectedNumberOfSummonerSpells AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfSummonerSpells = (SELECT COUNT(ss.Id) FROM [dbo].[SummonerSpell] ss)
			SET @ActualResult = 'The total number of summoner spells within the summonerspell table is ' + CAST(@ActualNumberOfSummonerSpells AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfSummonerSpells = @ActualNumberOfSummonerSpells
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