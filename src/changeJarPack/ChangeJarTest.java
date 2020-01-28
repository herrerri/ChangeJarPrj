package changeJarPack;

import javafx.scene.control.TextFormatter;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

public class ChangeJarTest {

	/**
	 *  Your assignment is to write many more test cases to
	 *  have a robust testing, more than just 100% coverage.
	 *
	 *  Some examples are provided to help you get started
	 */


	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
		assertEquals (6, jar1.getQuarters());
		assertEquals (5, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (2, jar1.getPennies());

		ChangeJar jar2 = new ChangeJar();
		assertEquals (0, jar2.getQuarters());
		assertEquals (0, jar2.getDimes());
		assertEquals (0, jar2.getNickels());
		assertEquals (0, jar2.getPennies());

		ChangeJar jar3 = new ChangeJar(jar1);
		assertEquals (6, jar3.getQuarters());
		assertEquals (5, jar3.getDimes());
		assertEquals (4, jar3.getNickels());
		assertEquals (2, jar3.getPennies());

		ChangeJar jar4 = new ChangeJar(1.49);
		assertEquals (5, jar4.getQuarters());
		assertEquals (2, jar4.getDimes());
		assertEquals (0, jar4.getNickels());
		assertEquals (4, jar4.getPennies());

		ChangeJar jar5 = new ChangeJar("1.49");
		assertEquals (5, jar4.getQuarters());
		assertEquals (2, jar4.getDimes());
		assertEquals (0, jar4.getNickels());
		assertEquals (4, jar4.getPennies());
	}

	// Testing error for constructor with negative quarters
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeQuarterConstructor(){
		ChangeJar jar = new ChangeJar(-6,5,4,2);
	}

	// Testing error for constructor with negative dimes
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeDimeConstructor(){
		ChangeJar jar = new ChangeJar(6,-5,4,2);
	}

	// Testing error for constructor with negative nickels
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeNickelConstructor(){
		ChangeJar jar = new ChangeJar(6,5,-4,2);
	}

	// Testing error for constructor with negative pennies
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativePennyConstructor(){
		ChangeJar jar = new ChangeJar(6,5,4,-2);
	}

	// Testing error for constructor with negative double amount
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeDoubleConstructor(){
		ChangeJar jar = new ChangeJar(-1.5);
	}

	// Testing error for constructor with excessive decimals in double amount
	@Test
			(expected = IllegalArgumentException.class)
	public void testExcessiveDecimalConstructor(){
		ChangeJar jar = new ChangeJar(1.501);
	}

	// Testing error for constructor with negative double AND excessive decimals
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeDoubleAndExcessiveDecimalConstructor(){
		ChangeJar jar = new ChangeJar(-1.501);
	}

	// Testing error for constructor with negative quarter values
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeOtherChangeJarConstructor(){
		ChangeJar jar1 = new ChangeJar();
		jar1.setQuarters(-4);
		ChangeJar jar2 = new ChangeJar(jar1);
	}

	// Testing error for constructor with negative string amount
	@Test
			(expected = IllegalArgumentException.class)
	public void testNegativeStringConstructor(){
		ChangeJar jar = new ChangeJar("-1.5");
	}

	// Testing error for constructor with non-numeric string amount
	@Test
			(expected = IllegalArgumentException.class)
	public void testAlphaCharacterStringConstructor(){
		ChangeJar jar = new ChangeJar("ABC");
	}

	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeout(1,1,1,1);
		assertEquals (2, jar.getQuarters());
		assertEquals (2, jar.getDimes());
		assertEquals (1, jar.getNickels());
		assertEquals (1, jar.getPennies());
	}

	// testing invalid takeOut with negative quarters
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut2() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeout(-1,1,1,1);
	}

	// testing invalid takeOut with negative dimes
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut3() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeout(1,-1,1,1);
	}

	// testing invalid takeOut with negative nickels
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut4() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeout(1,1,-1,1);
	}

	// testing invalid takeOut with negative pennies
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut5() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeout(1,1,1,-1);
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut6() {
		ChangeJar jar1 = new ChangeJar(5,3,4,3);
		ChangeJar jar2 = jar1.takeout(1.22);

		assertEquals (1, jar1.getQuarters());
		assertEquals (1, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (1, jar1.getPennies());

		assertEquals (4, jar2.getQuarters());
		assertEquals (2, jar2.getDimes());
		assertEquals (0, jar2.getNickels());
		assertEquals (2, jar2.getPennies());
	}

	// testing invalid takeOut with negative amount
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut7() {
		ChangeJar jar1 = new ChangeJar(5,3,4,3);
		ChangeJar jar2 = jar1.takeout(-1.22);
	}

	// testing invalid takeOut with negative ChangeJar quarters
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut8() {
		ChangeJar jar1 = new ChangeJar(5,3,4,3);
		jar1.setQuarters(-5);
		ChangeJar jar2 = new ChangeJar(5, 3,4,3);
		jar2.takeout(jar1);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeout9(){
		ChangeJar jar = new ChangeJar(3,2,0,0);
		jar.takeout(0.8);
	}

	// testing add for valid low numbers
	@Test
	public void testAdd() {
		ChangeJar jar = new ChangeJar();
		jar.add(2,3,4,5);
		assertEquals (2, jar.getQuarters());
		assertEquals (3, jar.getDimes());
		assertEquals (4, jar.getNickels());
		assertEquals (5, jar.getPennies());
	}

	@Test
	public void testAdd2() {
		ChangeJar jar1 = new ChangeJar();
		ChangeJar jar2 = new ChangeJar(1,2,3,4);
		jar1.add(jar2);
		assertEquals (1, jar1.getQuarters());
		assertEquals (2, jar1.getDimes());
		assertEquals (3, jar1.getNickels());
		assertEquals (4, jar1.getPennies());
	}

	// testing add for invalid numbers
	@Test
			(expected = IllegalArgumentException.class)
	public void testInvalidAdd() {
		ChangeJar jar = new ChangeJar();
		jar.add(-2,3,4,5);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testInvalidAdd2() {
		ChangeJar jar = new ChangeJar();
		jar.add(2,-3,4,5);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testInvalidAdd3() {
		ChangeJar jar = new ChangeJar();
		jar.add(2,3,-4,5);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testInvalidAdd4() {
		ChangeJar jar = new ChangeJar();
		jar.add(2,3,4,-5);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testInvalidAdd5() {
		ChangeJar jar1 = new ChangeJar();
		ChangeJar jar2 = new ChangeJar();
		jar2.setQuarters(-5);
		jar1.add(jar2);
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(jar1.equals(jar2));
		assertTrue(jar1.equals(jar3));
	}

	// Testing class equals for valid numbers
	@Test
	public void testClassEquals () {
		ChangeJar jar1 = new ChangeJar(1,1,1,1);
		ChangeJar jar2 = new ChangeJar(2,2,2,2);
		ChangeJar jar3 = new ChangeJar(1,1,1,1);

		assertFalse(ChangeJar.equals(jar1,jar2));
		assertTrue(ChangeJar.equals(jar1,jar3));
	}

	// Testing equals for invalid objects
	@Test
	public void testEqualsNonChangeJar () {
		ChangeJar jar1 = new ChangeJar();
		String s = "Should not be equal to jar1";

		assertFalse(jar1.equals(s));
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(jar2.compareTo(jar1) > 0);
		assertTrue(jar3.compareTo(jar1) < 0);
		assertTrue(jar1.compareTo(jar4) == 0);
	}

	// testing class compareTo all returns
	@Test
	public void testClassCompareTo () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(ChangeJar.compareTo(jar2, jar1) > 0);
		assertTrue(ChangeJar.compareTo(jar3, jar1) < 0);
		assertTrue(ChangeJar.compareTo(jar1, jar4) == 0);
	}

	// testing mutate on/off
	@Test
	public void testMutateOff(){
		ChangeJar.mutate(false);
		assertFalse(ChangeJar.MUTATE);

		ChangeJar.mutate(true);
		assertTrue(ChangeJar.MUTATE);
	}

	// load and save combined.
	@Test
	public void testLoadSave() throws FileNotFoundException {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);

		jar1.save("file1");
		jar1 = new ChangeJar();  // resets to zero

		jar1.load("file1");
		assertTrue(jar1.equals(jar2));
	}

	// attempting to load a ChangeJar with negative values
	@Test
			(expected = IllegalArgumentException.class)
	public void testLoadNegative() throws FileNotFoundException {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

		jar1.load("file2");
	}

	// loading a non-existent ChangeJar
	@Test
			(expected = FileNotFoundException.class)
	public void testLoadFalse() throws FileNotFoundException {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

		jar1.load("file3");
	}

	// testing penny decrease with valid pennies
	@Test
	public void testDecrease(){
		ChangeJar jar = new ChangeJar(0,0,0,1);
		jar.dec();
	}

	// testing penny increase with no ceiling
	@Test
	public void testIncrease(){
		ChangeJar jar = new ChangeJar();
		jar.inc();
		assertEquals(1,jar.getPennies());
	}

	// testing illegal penny decrease
	@Test
			(expected = IllegalArgumentException.class)
	public void testNotEnoughPennies() {
		ChangeJar jar1 = new ChangeJar(0,0,0,0);
		jar1.dec();
	}

	// testing negative number for nickels in takeOut
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickels() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeout(1,1,-1,1);
	}

	// testing negative number for dimes in takeOut
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimes() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeout(1,-1,1,1);
	}

	// testing negative number quarters for the constructor
	@Test
			(expected = IllegalArgumentException.class)
	public void testConstructorNegQuarters() {
		new ChangeJar(-300, 0, 0, 0);
	}

	// testing getting coins
	@Test
	public void testGetCoins(){
		ChangeJar jar = new ChangeJar(1,2,3,4);

		assertEquals(jar.getPennies(),4);
		assertEquals(jar.getNickels(), 3);
		assertEquals(jar.getDimes(), 2);
		assertEquals(jar.getQuarters(), 1);
	}

	// testing setting coins()
	@Test
	public void testSetCoins(){
		ChangeJar jar = new ChangeJar();
		jar.setQuarters(1);
		jar.setDimes(2);
		jar.setNickels(3);
		jar.setPennies(4);

		assertEquals(jar.getPennies(),4);
		assertEquals(jar.getNickels(), 3);
		assertEquals(jar.getDimes(), 2);
		assertEquals(jar.getQuarters(), 1);
	}
}
