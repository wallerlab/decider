package theDecider

public enum BenchmarkSystem{
	CH32O_C6F6_LP_TOWARDS_ECLIPSED_3PT0A("c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C"),
	CH32O_C6H5NO2_LP_TOWARDS_ECLIPSED_3PT1A("c1ccc(cc1)[N+](=[O-])O.O(C)C"),
	CH32O_C6H5CN_LP_TOWARDS_ECLIPSED_3PT1A("c1ccc(cc1)C#N.O(C)C"),
	CH32O_C6H5CF3_LP_TOWARDS_ECLIPSED_F_AWAY_3PT1A("c1ccc(cc1)C(F)(F)F.O(C)C"),
	CH32O_C6H5BR_LP_TOWARDS_ECLIPSED_3PT1A("c1ccc(cc1)Br.O(C)C"),
	CH32O_C6H5CL_LP_TOWARDS_ECLIPSED_3PT1A("c1ccc(cc1)Cl.O(C)C"),
	CH32O_C6H5F_LP_TOWARDS_ECLIPSED_3PT1A("c1ccc(cc1)F.O(C)C"),
	CH32O_C6H6_LP_TOWARDS_ECLIPSED_3PT2A("c1ccccc1.O(C)C"),
	CH32O_C6H5CH3_LP_TOWARDS_STAGGERED_H_TOWARDS_3PT1A("c1ccc(cc1)C.O(C)C"),
	CH32O_C6H5OCH3_LP_TOWARDS_STAGGERED_O_LP_AWAY_3PT1A("c1ccc(cc1)OC.O(C)C"),
	CH32O_C6H5NCH32_LP_TOWARDS_ECLIPSED_N_LP_TOWARDS_3PT1A("c1ccc(cc1)N(C)C.O(C)C"),
	CH32O_C6F6_LP_AWAY_ECLIPSED_4PT5A("c1(c(c(c(c(c1F)F)F)F)F)F.O(C)C"),
	CH32O_C6H5NO2_LP_AWAY_ECLIPSED_4PT6A("c1ccc(cc1)[N+](=[O-])O.O(C)C"),
	CH32O_C6H5CN_LP_AWAY_ECLIPSED_4PT6A("c1ccc(cc1)C#N.O(C)C"),
	CH32O_C6H5CF3_LP_AWAY_ECLIPSED_F_TOWARDS_4PT6A("c1ccc(cc1)C(F)(F)F.O(C)C"),
	CH32O_C6H5BR_LP_AWAY_ECLIPSED_4PT6A("c1ccc(cc1)Br.O(C)C"),
	CH32O_C6H5CL_LP_AWAY_ECLIPSED_4PT6A("c1ccc(cc1)Cl.O(C)C"),
	CH32O_C6H5F_LP_AWAY_ECLIPSED_4PT6A("c1ccc(cc1)F.O(C)C"),
	CH32O_C6H6_LP_AWAY_ECLIPSED_4PT6A("c1ccccc1.O(C)C"),
	CH32O_C6H5CH3_LP_AWAY_ECLIPSED_H_TOWARDS_4PT6A("c1ccc(cc1)C.O(C)C"),
	CH32O_C6H5OCH3_LP_AWAY_ECLIPSED_O_LP_TOWARDS_4PT6A("c1ccc(cc1)OC.O(C)C"),
	CH32O_C6H5NCH32_LP_AWAY_ECLIPSED_N_LP_TOWARDS_4PT5A("c1ccc(cc1)N(C)C.O(C)C"),
	S22_1_01A("N.N"),
	S22_2_02A("O.O"),
	S22_3_03A("C(=O)O.C(=O)O"),
	S22_4_04A("C(=O)N.C(=O)N"), 
	S22_5_05A("O=c1[nH]ccc(=O)[nH]1.O=c1[nH]ccc(=O)[nH]1"), 
	S22_6_06A_06B("O=c1[nH]cccc1.n1ccccc1N"), 
	S22_7_07A_07B("n1cnc2[nH]cnc2c1N.[nH]1cc(c(=O)[nH]c1=O)C"), 
	S22_8_08A("C.C"), 
	S22_9_09A("C=C.C=C"), 
	S22_10_10A_10B("c1ccccc1.C"), 
	S22_11_11A("c1ccccc1.c1ccccc1"), 
	S22_12_12A("c1cnccn1.c1cnccn1"), 
	S22_13_13A("[nH]1ccc(=O)[nH]c1=O.[nH]1ccc(=O)[nH]c1=O"), 
	S22_14_14A_14B("c1cc2c(cccc2)[nH]1.c1ccccc1"), 
	S22_15_15A_15B("[nH]1c2ncnc(N)c2nc1.[nH]1cc(C)c(=O)[nH]c1=O"), 
	S22_16_16A_16B("C=C.C#C"), 
	S22_17_17A_17B("c1ccccc1.O"), 
	S22_18_18A_18B("c1ccccc1.N"), 
	S22_19_19A_19B("c1ccccc1.N#C"), 
	S22_20_20A_20B("c1ccccc1.c1ccccc1"), 
	S22_21_21A_21B("c1cc2c(cccc2)[nH]1.c1ccccc1"), 
	S22_22_22A_22B("Oc1ccccc1.c1(ccccc1)O")
	
	private String smilesString
	
	private BenchmarkSystem(String smilesString){
		this.smilesString = smilesString
	}
	
	private String smilesString(){
		return smilesString
	}

}
