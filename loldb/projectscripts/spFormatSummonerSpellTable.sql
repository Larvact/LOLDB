USE loldb;
GO
CREATE OR ALTER PROCEDURE [dbo].[spFormatSummonerSpellTable]
AS
	SET NOCOUNT ON;
	UPDATE [dbo].[SummonerSpell]
	SET Description = 'Teleports your champion a short distance toward your cursor''s location.'
	WHERE Name = 'Flash'
	UPDATE [dbo].[SummonerSpell]
	SET Description = 'Restores 50% of your champion''s maximum Mana. Also restores allies for 25% of their maximum Mana.'
	WHERE Name = 'Clarity'
	UPDATE [dbo].[SummonerSpell]
	SET Description = 'Quickly travel to the Poro King''s side.'
	WHERE Name = 'To the King!'
GO