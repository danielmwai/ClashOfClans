--print("战斗中的公式处理\n")
--start
--普通攻击公式：
--arg[1]=attacker:getAttack() 
--arg[2]=victim:getHp() 
--arg[3]=victim:getDefense()
function normalAttack(...)
--	for i,v in ipairs(arg)--遍历数组
--	do
--		print("fun["..i.."]="..arg[i])
--	end
	if arg[2]<=0 then
		return 0
	else
		local hp = (arg[2]-(arg[1]-arg[3]*3/5)) --【3/5==60%】
		if hp<=0 then
			return 0
		else
			return hp
		end
	end
end
--end


--start
--技能公式
--arg[1]=attacker:getAttack()
--arg[2]=skill:getLevel()
--arg[3]=skill:getBaseDamage()
--arg[4]=skill:getCast():getDuration() *最小值为1*
--arg[5]=victim:getHp()
--arg[6]=victim:getDefense()
function skillHurt(...)
	local hurt = ((arg[1]+(arg[2]*arg[3]*0.5))-arg[6]*1/5)/arg[4]*3
	local hp = arg[5]-hurt
	if hp>0 then
		return hp
	else
		return 0
	end
end

--技能公式 1
--arg[1]=attacker:getAttack()
--arg[2]=skill:getLevel()
--arg[3]=skill:getBaseDamage()
--arg[4]=skill:getCast():getDuration() *最小值为1*
--arg[5]=victim:getHp()
--arg[6]=victim:getDefense()
function skillHurt_1(...)
	local hurt = ((arg[1]+(arg[2]*arg[3]*0.5))-arg[6])/arg[4]*3
	local hp = arg[5]-hurt
	if hp>0 then
		return hp
	else
		return 0
	end
end
--end

--start
--buffer公式 返回影响后的数值
--arg[1]=buffer:getBufferType():getNumber()
--arg[2]=buffer:getAffectValue()
--arg[3]=warrior:getMaxHp()
--arg[4]=warrior:对应arg[0]的五项基本属性之一
function effectBuffer(...)
--  debug
--	for i,v in ipairs(arg)
--	do
--		print("fun["..i.."]="..arg[i])
--	end
	local hurt = 0
	local type = arg[1]
	if  type == 0 then
		return hurt
	elseif type == 1 then
		hurt = arg[4]+arg[1]*(1+arg[2])
	elseif type == 2 then
		hurt = arg[4]*(1+arg[2])
	elseif type == 3 then
		hurt = arg[4]*(1+arg[2])
	elseif type == 4 then
		hurt = arg[4]*(1+arg[2])
	elseif type == 5 then
		hurt = arg[4]*(1+arg[2])
	elseif type == 6 then
		hurt = arg[4]*(1-arg[2])
	elseif type == 7 then
		hurt = arg[4]*(1-arg[2])
	elseif type == 8 then
		hurt = arg[4]*(1-arg[2])
	elseif type == 9 then
		hurt = arg[4]*(1-arg[2])
	else
		hurt = arg[4]*(1-arg[2])
	end
return hurt
end
--end