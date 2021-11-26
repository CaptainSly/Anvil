enumStats = luajava.bindClass(enumStat)

function onUse(actor)
	actor:modifyActorStat(enumStats.HP, 15)
end