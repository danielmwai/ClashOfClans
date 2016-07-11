--print("战斗前的公式处理\n")
--战前生命值 =（初始生命值+军衔*30）*（1+buffer效果）					
--战前攻击力 =（初始攻击力+军衔*6）*（1+buffer效果）					
--战前防御力 =（初始防御力+军衔*1.8）*（1+buff效果）					
--战前攻速      =（初始攻速-军衔*0.005）*（1+buffer效果）					
--战前移速      = 初始速度*（1-军衔*0.005）*（1+buffer效果）
function noBufferHP(warrior)
	return warrior:getHp()+warrior:getRankID()*30
end

function noBufferATC(warrior)
	return warrior:getAttack()+warrior:getRankID()*6
end

function noBufferDF(warrior)
	return warrior:getDefense()+warrior:getRankID()*1.8
end

function noBufferAS(warrior)
	return warrior:getAtcSpeed()-warrior:getRankID()*0.005
end

function noBufferMS(warrior)
	return warrior:getMoveSpeed()*(1-warrior:getRankID()*0.005)
end

function preWarProperty(warrior)
--	print("haha--1--"..noBufferHP(warrior))
--	print("haha--2--"..noBufferATC(warrior))
--	print("haha--3--"..noBufferDF(warrior))
--	print("haha--4--"..noBufferAS(warrior))
--	print("haha--5--"..noBufferMS(warrior))
	local hp,atc,df,as,ms=noBufferHP(warrior),noBufferATC(warrior),noBufferDF(warrior),noBufferAS(warrior),noBufferMS(warrior)
--	print("haha--n--")
	if warrior:getSkillInfoCount()>0 then
		local hp = 0
		local skillCount = warrior:getSkillInfoCount()
		local skillList = warrior:getSkillInfoList()
		for i=0,skillCount-1,1
		do
			local skill=skillList:get(i)
			if skill:getCast():getReleaseType()==0 then --被动技能
				local targetBufferList=skill:getTargetBufferList()
				local targetBufferSize=targetBufferList:size()
				if targetBufferSize>0 then
					for j=0,targetBufferSize-1,1
					do
						local buffer = targetBufferList:get(j)
						local affectValue=1+buffer:getAffectValue()
						if buffer:getIsStatic() then
							local bufferType=buffer:getBufferType():getNumber()
							if bufferType==1 then
								hp=hp*affectValue
							elseif bufferType==2 then
								atc=atc*affectValue
							elseif bufferType==3 then
								df=df*affectValue
							elseif bufferType==4 then
								as=as*affectValue
							elseif bufferType==5 then
								ms=ms*affectValue
							else
								print("type nil!\0")
							end
						end
					end
				end
			end
		end 
	end
	return hp,atc,df,as,ms
end