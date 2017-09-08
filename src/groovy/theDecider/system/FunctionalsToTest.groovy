package theDecider.system

/**
 * All functionals in S22 database, with whether they can be run in gaussian
 * or orca
 *
 * Created by suzanne on 04.08.17.
 */
class FunctionalsToTest {

    Map gaussian = ["B1B95":"B1B95", "B1B95-D3":"B1B95", "B2PLYP":"B2PLYP",
                    "B2PLYP-D3":"B2PLYPD3", "B3LYP":"B3LYP", "B3LYP-D3":"B3LYP",
                    "B3PW91":"B3PW91", "B3PW91-D3":"B3PW91", "B97":"B97D",
                    "B97-D3":"B97D3", "BHLYP":"BHandHLYP", "BHLYP-D3":"BHandHLYP",
                    "BLYP":"BLYP", "BLYP-D3":"BLYP", "BMK":"BMK", "BMK-D3":"BMK",
                    "BP86":"BP86", "BP86-D3":"BP86", "BPBE":"BPBE", "BPBE-D3":"BPBE",
                    "CAM-B3LYP":"CAM-B3LYP", "CAM-B3LYP-D3":"CAM-B3LYP",
                    "LC-wPBE":"LC-wPBE", "LC-wPBE-D3":"LC-wPBE", "M05":"M05",
                    "M05-D3":"M05", "M052X":"M052X", "M052X-D3":"M052X", "M06":"M06",
                    "M06-D3": "M06", "M062X":"M062X", "M062X-D3":"M062X",
                    "M06HF":"M06HF", "M06HF-D3":"M06HF", "M06L":"M06L",
                    "M06L-D3":"M06L", "MPW1B95":"MPW1B95", "MPW1B95-D3":"MPW1B95",
                    "MPWB1K":"MPWB1K", "MPWB1K-D3":"MPWB1K", "mPWLYP":"mPW1LYP",
                    "mPWLYP-D3":"mPW1LYP", "OLYP":"OLYP", "OLYP-D3":"OLYP",
                    "OPBE":"OPBE", "OPBE-D3":"OPBE", "oTPSS":"OTPSS",
                    "oTPSS-D3":"OTPSS", "PBE":"PBEPBE", "PBE-D3":"PBEPBE",
                    "PBE0":"PBE1PBE", "PBE0-D3":"PBE1PBE", "PBEsol":"PBEPBE",
                    "PBEsol-D3":"PBEPBE", "PTPSS":"PTPSS",
                    "PTPSS-D3":"PTPSS", "PW6B95":"PW6B95", "PW6B95-D3":"PW6B95D3",
                    "revPBE":"PBEh1PBE", "revPBE-D3":"PBEh1PBE", "SVWN":"SVWN",
                    "TPSS":"TPSS", "TPSS-D3":"TPSS", "TPSSh":"TPSSh",
                    "TPSSh-D3":"TPSSh", "wB97X-D":"wB97XD", "XYG3":"XYG3"]
    Map orca = ["B2GPPLYP":"B2GP-PLYP", "B2GPPLYP-D3":"B2GP-PLYP D3ZERO",
                "DSD-BLYP":"B3LYP", "DSD-BLYP-D3":"B3LYP D3ZERO",
                "PWPB95":"PWPB95", "PWPB95-D3":"PWPB95 D3ZERO",
                "revPBE0": "! revPBE0 NL", "revPBE0-D3": "! revPBE0 NL D3",
                "revPBE38": "! revPBE38 NL", "revPBE38-D3": "! revPBE38 NL D3",
                "rPW86PBE":"! rPW86PBE NL", "rPW86PBE-D3":"! rPW86PBE NL D3",
                "TPSS0":"TPSS0", "TPSS0-D3":"TPSS0 D3ZERO"]
    Map tbd = ["BOP":null, "BOP-D3":null, "PBE38":null, "PBE38-D3":null, "PWB6K":null,
               "PWB6K-D3":null, "revSSB": null, "revSSB-D3": null,
               "SPW92": null, "SSB": null, "SSB-D3": null]
    Map functionals = ["gaussian": gaussian, "orca": orca, "tbd": tbd]


    Map getFunctionals(String softwareName){
        return functionals(softwareName)
    }

    boolean isGaussianFunctional(String functionalName){
        return gaussian.containsKey(functionalName)
    }

    boolean isOrcaFunctional(String functionalName){
        return orca.containsKey(functionalName)
    }

    boolean isTestableFunctional(String functionalName){
        if(!isGaussianFunctional(functionalName)){
            return isOrcaFunctional(functionalName)
        }
        return isGaussianFunctional(functionalName)
    }

    String getKeyword(String functionalName){

        for(software in functionals){
            if(software(functionalName)){
                return software(functionalName)
            }
        }
        return null
    }

}
