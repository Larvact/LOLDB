USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spRoleTableTest] (@ExpectedNumberOfRoles SMALLINT)
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the correct number of roles have been added to the roles table*/
		DECLARE @ActualNumberOfRoles SMALLINT;
		SET @TestDescription = 'Ensure that the total number of rows within the role table is ' + CAST(@ExpectedNumberOfRoles AS VARCHAR(255));
		SET @ExpectedResult = 'The total number of roles within the role table is ' + CAST(@ExpectedNumberOfRoles AS VARCHAR(255));

		BEGIN TRY 
			SET @ActualNumberOfRoles = (SELECT COUNT(r.Id) FROM [dbo].[Role] r)
			SET @ActualResult = 'The total number of roles within the role table is ' + CAST(@ExpectedNumberOfRoles AS VARCHAR(255));
		END TRY
		BEGIN CATCH
			SET @ActualResult = (SELECT ERROR_MESSAGE());
		END CATCH

		IF @ExpectedNumberOfRoles = @ActualNumberOfRoles
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