package theDecider.system

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin

import java.text.DecimalFormat

import spock.lang.*
import theDecider.models.Strategy
import theDecider.models.MolecularSystem
import theDecider.system.SimpleComplexityCalculator;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SimpleComplexityCalculatorSpec extends Specification {
	
	SimpleComplexityCalculator scc = new SimpleComplexityCalculator()
	
	@Unroll
	void "test that calculateFunctionalComplexity can handle different functional categories"(){
		expect:
		b == scc.calculateFunctionalComplexity(a)
		
		where:
		a					|	b
		"LDA"				|	1
		"GGA"				|	2
		"meta-GGA"			|	3
		"hybrid-GGA"		|	4
		"double-hybrid-GGA"	|	5
		"wavefunction"		|	1
		"unclassified"		|	2
		"semiempirical"		|	2
		"forcefield"		|	2
	}
	
	@Unroll
	void "test that getNumZeta returns correct numZeta"(){
		setup:
		scc.userSystemComplexityFactor = 100/10
		DecimalFormat df = new DecimalFormat("#.####")
		
		expect:
		b == Double.parseDouble(df.format(scc.getNumZeta(a)))
		
		where:
		a					|	b
		"SVP"				|	1.518
		"DZVP"				|	3.036
		"def2-TZVP"			|	4.554
		"def2-QZVP"			|	6.072
		"cc-pVDZ"			|	3.036
		"aug-cc-pVTZ"		|	4.554
		"aug-cc-pVQZ"		|	6.072
		"aug-cc-pV5Z"		|	7.590
		"LACVP*"			|	0
		"LACVP* w/CP"		|	0
		"aug-cc-pVQZ w/CP"	|	6.072
		"def2-TZVP w/CP"	|	4.554
	}
	
	@Unroll
	void "test that getNumPolar returns correct numPolar"(){
		setup:
		scc.userSystemComplexityFactor = 100/10
		DecimalFormat df = new DecimalFormat("#.####")
		
		expect:
		b == Double.parseDouble(df.format(scc.getNumPolar(a)))
		
		where:
		a					|	b
		"SVP"				|	2.7684
		"DZVP"				|	2.7684
		"def2-TZVP"			|	2.7684
		"def2-QZVP"			|	2.7684
		"cc-pVDZ"			|	2.7684
		"aug-cc-pVTZ"		|	2.7684
		"aug-cc-pVQZ"		|	2.7684
		"LACVP*"			|	6.5559
		"LACVP* w/CP"		|	6.5559
		"aug-cc-pVQZ w/CP"	|	2.7684
		"def2-TZVP w/CP"	|	2.7684
	}
	
	@Unroll
	void "test that getNumDiffuse returns correct numDiff"(){
		setup:
		scc.userSystemComplexityFactor = 100/10
		
		expect:
		b == scc.getNumDiffuse(a)
		
		where:
		a					|	b
		"SVP"				|	0.0
		"DZVP"				|	0.0
		"def2-TZVP"			|	0.0
		"def2-QZVP"			|	0.0
		"cc-pVDZ"			|	0.0
		"aug-cc-pVTZ"		|	1.3573
		"aug-cc-pVQZ"		|	1.3573
		"LACVP*"			|	0.0
		"LACVP* w/CP"		|	0.0
		"aug-cc-pVQZ w/CP"	|	1.3573
		"def2-TZVP w/CP"	|	0.0
	}
	
	@Unroll
    void "test that calculateBasisSetComplexity can handle different basis sets in Jacob DB"() {
		setup:
		scc.userSystemComplexityFactor = 100/10
		DecimalFormat df = new DecimalFormat("#.####")
		
		expect:
		def params = b
		c == Double.parseDouble(df.format(scc.calculateBasisSetComplexity(a, params)))
		
		where:
		a					|	b												|	c
		"SVP"				|	["alpha": "100", "beta": "100", "gamma": "100"]	|	4.2864
		"SVP"				|	["alpha": "50", "beta": "100", "gamma": "100"]	|	3.5274
		"SVP"				|	["alpha": "100", "beta": "50", "gamma": "100"]	|	2.9022
		"SVP"				|	["alpha": "100", "beta": "100", "gamma": "50"]	|	4.2864
		"def2-QZVP"			|	["alpha": "100", "beta": "100", "gamma": "100"]	|	8.8404
		"def2-TZVP"			|	["alpha": "100", "beta": "100", "gamma": "100"]	|	7.3224
		"DZVP"				|	["alpha": "100", "beta": "100", "gamma": "100"]	|	5.8044
		"cc-pVDZ"			|	["alpha": "100", "beta": "100", "gamma": "100"]	|	5.8044
		"VDZ"				|	["alpha": "100", "beta": "100", "gamma": "100"]	|	3.2794
		"aug-cc-pVTZ"		|	["alpha": "100", "beta": "100", "gamma": "100"]	|	8.6797
		"aug-cc-pVQZ"		|	["alpha": "100", "beta": "100", "gamma": "100"]	|	10.1977
		"LACVP*"			|	["alpha": "100", "beta": "100", "gamma": "100"]	|	6.5559
		"LACVP* w/CP"		|	["alpha": "100", "beta": "100", "gamma": "100"]	|	6.5559
		"aug-cc-pVQZ w/CP"	|	["alpha": "100", "beta": "100", "gamma": "100"]	|	10.1977
		"def2-TZVP w/CP"	|	["alpha": "100", "beta": "100", "gamma": "100"]	|	7.3224
		"CBS"				|	["alpha": "100", "beta": "100", "gamma": "100"]	|	1.0
    }
}
