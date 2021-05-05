USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSpecificChampionRoleTest] (@SelectedChampion VARCHAR(32), @ExpectedRole VARCHAR(16))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the selected champion is connected to the expected role via the championrole table*/
		SET @TestDescription = 'Ensure that the champion ' + @SelectedChampion + ' is connected to the role ' + @ExpectedRole + ' via the championrole table';
		SET @ExpectedResult = @SelectedChampion + ' is connected to the role ' + @ExpectedRole + ' via the championrole table';

		BEGIN TRY
			IF Exists
			(SELECT 1 FROM [dbo].[Champion] c
				JOIN [dbo].[ChampionRole] cr
				ON c.Id = cr.ChampionId
				JOIN [dbo].[Role] r
				ON cr.RoleId = r.Id
				WHERE c.Name = @SelectedChampion AND r.Name = @ExpectedRole
			)
				BEGIN
					SET @ActualResult = @SelectedChampion + ' is connected to the role ' + @ExpectedRole + ' via the championrole table';
					SET @TestOutcome = 1;
				END
			ELSE
				BEGIN
					SET @ActualResult = @SelectedChampion + ' is not connected to the role ' + @ExpectedRole + ' via the championrole table';
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