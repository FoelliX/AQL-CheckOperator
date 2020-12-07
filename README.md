![Java 8](https://img.shields.io/badge/java-8-brightgreen.svg)
---
<p align="center">
	<img src="https://FoelliX.github.io/AQL-System/logo.png" width="300px"/>
</p>

# AQL-CheckOperator
The AQL-CheckOperator (used by [CoDiDroid](https://foellix.github.io/CoDiDroid/)) takes two AQL-Answers and tries to confirm the flows found in one answer by the flows found in the second one.

### Setup
To use the operator in any tool employing the [AQL-System](https://foellix.github.io/AQL-System), the following lines must be added to its configuration:
```XML
<config>
	...
	<operators>
		...
		<tool name="CHECK" version="1" external="false">
			<priority>1</priority>
			<execute>
				<run>java -jar AQL-CheckOperator-0.1.0-SNAPSHOT.jar %ANSWERS%</run>
                <result>/path/to/AQL-CheckOperator/result_%ANSWERSHASH%.xml</result>
                <instances>0</instances>
                <memoryPerInstance>2</memoryPerInstance>
			</execute>
			<path>/path/to/AQL-CheckOperator/</path>
			<questions>CHECK(2)</questions>
		</tool>
	</operators>
</config>
```

## Publications
- *Together Strong: Cooperative Android App Analysis* (Felix Pauck, Heike Wehrheim)  
ESEC/FSE 2019 [https://dl.acm.org/citation.cfm?id=3338915](https://dl.acm.org/citation.cfm?id=3338915)

## License
The AQL-System is licensed under the *GNU General Public License v3* (see [LICENSE](https://github.com/FoelliX/AQL-System/blob/master/LICENSE)).

# Contact
**Felix Pauck** (FoelliX)  
Paderborn University  
fpauck@mail.uni-paderborn.de  
[http://www.FelixPauck.de](http://www.FelixPauck.de)

# Links
- The AQL-System is part of CoDiDroid: [https://github.com/FoelliX/AQL-System](https://github.com/FoelliX/AQL-System)
- and in the CoDiDroid framework: [https://github.com/FoelliX/CoDiDroid](https://github.com/FoelliX/CoDiDroid)