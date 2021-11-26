-- bind to classes we need
local equipment = luajava.bindClass(classEquipment)
local equipmentSlot = luajava.bindClass(enumEquipmentSlot)
local equipmentStat = luajava.bindClass(enumEquipmentStat)
local testEquip = luajava.newInstance(classEquipment, "testEquipment", "Test Equipment", enumEquipmentSlot.HEAD)
local testEvent = luajava.newInstance(classGameEvent, "Test Event", "TEST EVENT.lua")

registerGameEvent(testEvent)

print()