<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="KlausMappTiless" tilewidth="32" tileheight="32" tilecount="8" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="0">
  <image width="32" height="32" source="KlausDoor.png"/>
 </tile>
 <tile id="1">
  <properties>
   <property name="addScore" type="int" value="5"/>
  </properties>
  <image width="32" height="32" source="KlausCoin.png"/>
 </tile>
 <tile id="2">
  <properties>
   <property name="addHealth" type="int" value="25"/>
   <property name="addMedkit" type="bool" value="true"/>
  </properties>
  <image width="32" height="32" source="KlausMedkit.png"/>
 </tile>
 <tile id="3">
  <properties>
   <property name="damageHealth" type="int" value="10"/>
   <property name="dmgHealth" type="bool" value="true"/>
  </properties>
  <image width="32" height="32" source="KlausFire.png"/>
 </tile>
 <tile id="4">
  <properties>
   <property name="damageHealth" type="int" value="15"/>
   <property name="dmgHealth" type="bool" value="true"/>
  </properties>
  <image width="32" height="32" source="KlausGoo.png"/>
 </tile>
 <tile id="5">
  <properties>
   <property name="isSolid" type="bool" value="true"/>
  </properties>
  <image width="32" height="32" source="KlausWall.png"/>
 </tile>
 <tile id="6">
  <image width="32" height="32" source="KlausFloor.png"/>
 </tile>
 <tile id="7">
  <properties>
   <property name="isSolid" type="bool" value="true"/>
  </properties>
  <image width="32" height="32" source="KlausDesk.png"/>
 </tile>
</tileset>
