SELECT symbol, description, sellPrice - buyPrice FROM signals JOIN symbols ON signals.symbolId = symbols.id WHERE sellDate IS NOT NULL 

SELECT SUM(sellPrice - buyPrice) FROM signals JOIN symbols ON signals.symbolId = symbols.id WHERE sellDate IS NOT NULL AND sellPrice < buyPrice

SELECT symbol, description, buyDate, buyPrice, sellDate, sellPrice FROM signals JOIN symbols ON signals.symbolId = symbols.id