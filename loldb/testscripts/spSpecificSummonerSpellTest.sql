USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSpecificSummonerSpellTest] (@SelectedSummonerSpell VARCHAR(32))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the selected summoner spell exists within the summoner spell table*/
		SET @TestDescription = 'Ensure that the sumkmoner spell ' + @SelectedSummonerSpell + ' exits within the summonerspell table';
		SET @ExpectedResult = @SelectedSummonerSpell + ' exists within the summoner spell table';

		BEGIN TRY
			IF Exists
			(SELECT 1 FROM [dbo].[SummonerSpell] ss
				WHERE ss.Name = @SelectedSummonerSpell
			)
				BEGIN
					SET @ActualResult = @SelectedSummonerSpell + ' exists within the summonerspell table';
					SET @TestOutcome = 1;
				END
			ELSE
				BEGIN
					SET @ActualResult = @SelectedSummonerSpell + ' does not exist within the summonerspell table';
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