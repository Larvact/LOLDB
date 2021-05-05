USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spSpecificRoleTest] (@SelectedRole VARCHAR(16))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the selected role exists within the role table*/
		SET @TestDescription = 'Ensure that the role ' + @SelectedRole + ' exits within the role table';
		SET @ExpectedResult = @SelectedRole + ' exists within the role table';

		BEGIN TRY
			IF Exists
			(SELECT 1 FROM [dbo].[Role] r
				WHERE r.Name = @SelectedRole
			)
				BEGIN
					SET @ActualResult = @SelectedRole + ' exists within the role table';
					SET @TestOutcome = 1;
				END
			ELSE
				BEGIN
					SET @ActualResult = @SelectedRole + ' does not exist within the role table';
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