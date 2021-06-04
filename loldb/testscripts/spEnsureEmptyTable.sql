USE [loldb]
GO
CREATE OR ALTER PROCEDURE [test].[spEnsureEmptyTable] (@SchemaName VARCHAR(8), @SelectedTable VARCHAR(255))
AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @Sql NVARCHAR(MAX);
		DECLARE @Count BIGINT;
		DECLARE @DateTimeOfExecution DATETIME2 = CURRENT_TIMESTAMP;
		DECLARE @TestDescription VARCHAR(MAX);
		DECLARE @ExpectedResult VARCHAR(MAX);
		DECLARE @ActualResult VARCHAR(MAX);
		DECLARE @TestOutcome BIT;

		/*Test 1 Ensure that the declared table has no data in it*/
		SET @TestDescription = 'Ensure that the data base table: ' + QUOTENAME(@SchemaName) + '.' + QUOTENAME(@SelectedTable) + ' has no data inside it';
		SET @ExpectedResult = 'The data base table: ' + QUOTENAME(@SchemaName) + '.' + QUOTENAME(@SelectedTable) + ' has no data inside it';
		SET @Sql = N'SELECT @Count = COUNT(*) FROM ' + QUOTENAME(@SchemaName) + '.' + QUOTENAME(@SelectedTable) + ';'

		EXEC sp_executesql @Sql, N'@Count bigint OUTPUT', @Count = @Count OUTPUT;

		IF (@Count = 0)
			BEGIN
				SET @TestOutcome = 1;
				SET @ActualResult = 'The data base table: ' + QUOTENAME(@SchemaName) + '.' + QUOTENAME(@SelectedTable) + ' has no data inside it';
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
				SET @TestOutcome = 0;
			END
		ELSE
			BEGIN
				SET @TestOutcome = 0;
				SET @ActualResult = 'The data base table: ' + QUOTENAME(@SchemaName) + '.' + QUOTENAME(@SelectedTable) + ' has data inside it';
				INSERT INTO [test].[TestResults]
				VALUES (@DateTimeOfExecution, @TestDescription, @ExpectedResult, @ActualResult, @TestOutcome)
			END
	END