local enumStats = luajava.bindClass(enumStat)

function onUse(actor)
	anvilLog(rollDice("1d6+12"))
	actor:modifyActorStat(enumStats.HP, 15)
end