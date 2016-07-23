package theDecider.models

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NDimensionalMatrixSpec extends Specification {
	
	NDimensionalMatrix ndm = new NDimensionalMatrix()
	
	void "test getMatrix"(){
		setup:
		ndm = new NDimensionalMatrix([[1,2]:1,[2,2]:2,[0,0]:0])
		
		when:
		def value = ndm.getMatrix()
		
		then:
		value == ndm.matrix
	}
	
	void "test getMatrixDimensions"(){
		setup:
		ndm = new NDimensionalMatrix([[1,2]:1,[2,2]:2,[0,0]:0])
		
		when:
		def value = ndm.getMatrixDimensions()
		
		then:
		value == ndm.dimensions
	}
	
	@Unroll
	void "test getAt gets the value of matrix at indices"(){
		setup:
		ndm.matrix = a
		
		expect:
		def value = ndm.getAt(b)
		value == c
		
		where:
		a								|	b		|	c
		[[1,2]:1,[2,2]:2,[0,0]:0]		|	[1,2]	|	1
		[[1,2,3]:1,[2,2,3]:2,[0,0,3]:0]	|	[2,2,3]	|	2
		[[1,2,1]:1,[2,2,3]:2,[0,0,3]:0]	|	[1,2,3]	|	0
	}
	
	@Unroll
	void "test getNumAtIndex"(){
		setup:
		ndm.matrix = a
		
		expect:
		def value = ndm.getSizeAtDepth(b)
		value == c
		
		where:
		a								|	b	|	c
		[[1,2]:1,[2,2]:2,[0,0]:0]		|	1	|	2
		[[1,2,3]:1,[2,2,3]:2,[0,0,3]:0]	|	2	|	1
		[[1,2,3]:1,[2,2,3]:2,[0,0,3]:0]	|	0	|	3
	}
	
	void "test isEmpty"(){
		setup:
		ndm.matrix = a
		
		expect:
		def isEmpty = ndm.isEmpty()
		isEmpty == b
		
		where:
		a								|	b
		[[1,2]:1,[2,2]:2,[0,0]:0]		|	false
		[[1,2,3]:1,[2,2,3]:2,[1,0,3]:0]	|	false
		[:]								|	true
	}
	
	@Unroll
	void "test addAt"(){
		setup:
		ndm.matrix = a
		
		expect:
		ndm.addAt(85, b)
		ndm.getAt(b) == 85
		
		where:
		a												|	b
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,0,2]
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,2,1]
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,2,2]
		[[0,0,0]:6, [0,0,1]:1, [0,1,0]:3, [0,1,1]:4]	|	[1,1,1]
		[[0,0,0]:6, [0,0,1]:1, [0,1,0]:3, [0,1,1]:4]	|	[1,1,0]
		[[0,0,0]:6, [0,0,1]:1, [0,1,0]:3, [0,1,1]:4]	|	[1,0,2]
		[[0,0,0]:6, [0,0,1]:1, [0,1,0]:3, [0,1,1]:4]	|	[1,1,2]
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,2,3]
	}
	
	@Unroll
	void "test addAt adds dimensions"(){
		setup:
		ndm.matrix = [:]
		ndm.dimensions = a
		
		expect:
		ndm.addAt(85, b)
		ndm.dimensions == c
		
		where:
		a	|	b			|	c
		0	|	[0,0,2]		|	3
		0	|	[0,2,1,4]	|	4
	}
	
	@Unroll
	void "test addAt doesn't add if dimensions not right"(){
		setup:
		ndm.matrix = a
		if(a.isEmpty()){
			ndm.dimensions = 0
		}else{ndm.dimensions = a.keySet()[0].size()}
		
		expect:
		def bool = ndm.addAt(85, b)
		bool == c

		where:
		a												|	b			|	c
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,2,3,4]	| false
		[[0,0,1,0]:1, [0,0,0,1]:6, [0,1,0,0]:3]			|	[0,2,3]		| false
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	[0,2,3]		| true
		[:]												|	[0,2,3]		| true
	}
	
	@Unroll
	void "test getAll"(){
		setup:
		ndm.matrix = a
		ndm.nameMap = ["functional": 0, "basisSet":1, "system":2]
		
		expect:
		def sysList = ndm.getAll(b)
		sysList == c || sysList == d

		where:
		a												|	b				|	c	|	d
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	["basisSet"]	| [4,1]	|	[1,4]
		[[0,0,1,0]:1, [0,0,0,1]:6, [0,1,0,0]:3]			|	["functional"]	| [1,3]	|	[3,1]
		[[0,0,1]:1, [0,0,0]:6, [0,1,0]:3, [0,1,1]:4]	|	["functional"]	| [6,3]	|	[3,6]
	}
}
