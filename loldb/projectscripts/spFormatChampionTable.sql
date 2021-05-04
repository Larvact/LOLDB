USE loldb;
GO
CREATE OR ALTER PROCEDURE [dbo].[spFormatChampionTable]
AS
	SET NOCOUNT ON;
	UPDATE dbo.Champion 
	SET Title = 'the Serpent''s Embrace'
	WHERE Name = 'Cassiopeia'
	UPDATE dbo.Champion 
	SET Name = 'Kha''zix'
	WHERE Name = 'Khazix'
	UPDATE dbo.Champion 
	SET Title = 'the Thunder''s Roar'
	WHERE Name = 'Volibear'
	UPDATE dbo.Champion 
	SET Title = 'the Blade''s Shadow'
	WHERE Name = 'Talon'
	UPDATE dbo.Champion 
	SET Name = 'Cho''gath'
	WHERE Name = 'Chogath'
	UPDATE dbo.Champion 
	SET Name = 'Vel''koz'
	WHERE Name = 'Velkoz'
	UPDATE dbo.Champion 
	SET Name = 'Rek''sai'
	WHERE Name = 'Reksai'
	UPDATE dbo.Champion 
	SET Title = 'the Storm''s Fury'
	WHERE Name = 'Janna'
	UPDATE dbo.Champion 
	SET Name = 'Kog''maw'
	WHERE Name = 'Kogmaw'
	UPDATE dbo.Champion 
	SET Title = 'Demacia''s Wings'
	WHERE Name = 'Quinn'
GO