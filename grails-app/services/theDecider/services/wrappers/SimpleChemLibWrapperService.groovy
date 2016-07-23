package theDecider.services.wrappers

import org.openscience.cdk.ChemFile
import org.openscience.cdk.atomtype.CDKAtomTypeMatcher
import org.openscience.cdk.fingerprint.IBitFingerprint;
import org.openscience.cdk.fingerprint.HybridizationFingerprinter
import org.openscience.cdk.interfaces.IAtom
import org.openscience.cdk.interfaces.IAtomContainer
import org.openscience.cdk.interfaces.IAtomType
import org.openscience.cdk.io.PDBReader;
import org.openscience.cdk.smiles.SmilesParser
import org.openscience.cdk.similarity.Tanimoto
import org.openscience.cdk.silent.SilentChemObjectBuilder
import org.openscience.cdk.tools.CDKHydrogenAdder
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;
import org.openscience.cdk.tools.manipulator.AtomTypeManipulator

/**
 * CDK wrapper
 * 
 * @author suzanne
 *
 */
class SimpleChemLibWrapperService implements ChemLibWrapper {
	
	/**
	 * Calculates Tanimoto coefficient of given smiles strings
	 * 
	 * @param smilesString1
	 * @param smilesString2
	 * @return tanimoto
	 */
	@Override
	public Double getTanimoto(String smilesString1, String smilesString2) {
		def userFingerprint = getBitFingerprint(smilesString1)
		def dbFingerprint = getBitFingerprint(smilesString2)
		def tanimoto = Tanimoto.calculate(userFingerprint, dbFingerprint)
		return tanimoto;
	}

	/**
	 * Counts the number of atoms in a given smiles string
	 * 
	 * @param smilesString
	 * @return numAtoms
	 */
	@Override
	public int countAtoms(String smilesString) {
		SmilesParser smilesParser  = new SmilesParser(SilentChemObjectBuilder.getInstance())
		IAtomContainer molecule = smilesParser.parseSmiles(smilesString)
		CDKAtomTypeMatcher matcher = CDKAtomTypeMatcher.getInstance(molecule.getBuilder())
		for (IAtom atom : molecule.atoms) {
			IAtomType type = matcher.findMatchingAtomType(molecule, atom)
			AtomTypeManipulator.configure(atom, type)
		}
		CDKHydrogenAdder adder = CDKHydrogenAdder.getInstance(molecule.getBuilder())
		adder.addImplicitHydrogens(molecule)
		int hydrogens = 0
		for(IAtom atom : molecule.atoms){
			hydrogens += atom.getImplicitHydrogenCount()
		}
		return molecule.getAtomCount() + hydrogens;
	}

	/*
	 * Parses the smiles String into a Tanimoto-usable Object
	 * 
	 */
	private IBitFingerprint getBitFingerprint(String smilesString){
	
		def smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance())
		def fingerprinter = new HybridizationFingerprinter()
		
		IAtomContainer molecule = smilesParser.parseSmiles(smilesString)
		return fingerprinter.getBitFingerprint(molecule)
		
	}

}
