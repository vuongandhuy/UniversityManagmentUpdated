import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This UniversityTest class provides different methods of checking the behaviour
 * of the University class and the objects that it uses. It maintains a
 * reference to a University object called 'stirling' and populates it via
 * the loadData method.
 *
 * If you run this class via a call to main, main will create
 * a University object and call the go method which produces some useful output that
 * you can analyse.
 *
 * Implement the unit test methods for
 *
 * (i) tree walk method
 * (ii) tree find method
 * (iii) list search
 * (iv) list sorting
 * (v) graph loop
 *
 */
public class UniversityTest {
    University stirling = new University();

    public static void main(String[] args) {
        UniversityTest test = new UniversityTest();
        test.go();
    }

    @BeforeEach
    public void loadData() {
        stirling.clear();

        Professor[] prof_list = new Professor[]{new Professor("Alek Sib", 159, "aleksib@stir.ac.uk", "CSM", 1.0),
                new Professor("Jill Lai", 369, "jl@stir.ac.uk", "CSM", 0.5),
                new Professor("Kevin Jack", 789, "kj@stir.ac.uk", "CSM", 1.0),
                new Professor("Guy F", 654, "gf@stir.ac.uk", "BES/CSM", 0.75),
                new Professor("Loius V", 643, "lv@stir.ac.uk", "CSM", 0.25),
                new Professor("Lett A", 580, "la@stir.ac.uk", "CSM", 0.5),
                new Professor("Stephanie A", 999, "sa@stir.ac.uk", "BES/CSM", 1.0),};

        Student[] list = new Student[]{new Student("John J", 147, "jj@stir.ac.uk"),
                new Student("Kate K", 565, "kk@stir.ac.uk"),
                new Student("Loius L", 6542, "ll@stir.ac.uk"),
                new Student("Zhang Z", 805, "zz@stir.ac.uk"),
                new Student("Xing L", 100, "xl@stir.ac.uk"),
                new Student("Alma A", 753, "aa@stir.ac.uk"),
                new Student("Borba B", 888, "bb@stir.ac.uk"),
                new Student("Zeus L", 001, "zl@stir.ac.uk"),
                new Student("Laura Q", 075, "lq@stir.ac.uk"),
                new Student("Pierre A", 130, "pa@stir.ac.uk"),
                new Student("Jess Z", 010, "jz@stir.ac.uk"),
                new Student("Hugh J", 101, "hj@stir.ac.uk"),
                new Student("Paul P", 654, "pp@stir.ac.uk")}; // include here some MSc Students

        Module alg1 = new Module(147, "Algorithms 1");
        Module alg2 = new Module(199, "Algorithms 2");
        alg2.addPrerequisites(alg1);
        Module alg3 = new Module(201, "Algorithms 3");
        alg3.addPrerequisites(alg1);
        Module dst = new Module(250, "Data Structure");
        dst.addPrerequisites(alg1);
        dst.addPrerequisites(alg2);
        dst.addPrerequisites(alg3);
        Module ds = new Module(101, "Introduction to Data Science", new ArrayList<Module>(Arrays.asList(alg1)));
        Module ml = new Module(301, "Machine Learning");
        Module dl = new Module(401, "Deep Learning");
        dst.addPrerequisites(dl); //
        ds.addPrerequisites(alg1);
        ds.addPrerequisites(dst);
        ml.addPrerequisites(alg2);
        ml.addPrerequisites(alg3);
        ml.addPrerequisites(ds);
        dl.addPrerequisites(alg1);
        dl.addPrerequisites(ml);
        Module nlp = new Module(450, "Natural Language Processing");
        nlp.addPrerequisites(ml);
        nlp.addPrerequisites(dl);
        nlp.addPrerequisites(ds);
        Module cn = new Module(475, "Computer Networks", new ArrayList<Module>(Arrays.asList(alg1)));
        Module g = new Module(501, "Computer Graphics", new ArrayList<Module>(Arrays.asList(alg2)));

        Cohort a1 = new Cohort(dl,
                new BinaryTree(new Student[]{list[0], list[1], list[2], list[5]}), prof_list[0]);
        stirling.addClass(a1);

        Cohort a2 = new Cohort(ml,
                new BinaryTree(new Student[]{list[3], list[4], list[6]}), prof_list[1]);
        stirling.addClass(a2);

        Cohort a3 = new Cohort(alg3,
                new BinaryTree(new Student[]{list[6], list[10], list[11], list[10]}), prof_list[2]);
        stirling.addClass(a3);

        Cohort a4 = new Cohort(ds,
                new BinaryTree(new Student[]{list[5], list[4], list[0], list[11], list[10]}), prof_list[3]);
        stirling.addClass(a4);

        Cohort a5 = new Cohort(dst,
                new BinaryTree(new Student[]{list[0], list[1], list[6], list[7], list[8]}), prof_list[4]);
        stirling.addClass(a5);

        Cohort a6 = new Cohort(alg2,
                new BinaryTree(new Student[]{list[2], list[5], list[6], list[9]}), prof_list[5]);
        stirling.addClass(a6);

        Cohort a7 = new Cohort(alg1,
                new BinaryTree(new Student[]{list[7], list[8], list[9], list[10], list[11]}), prof_list[6]);
        stirling.addClass(a7);

    }

    /**
     * Print the contents of the ArrayList 'list' to standard output.
     *
     * @param list The list to print
     */
    public void printArrayList(ArrayList<Cohort> list) {
        for (Cohort v : list) {
            System.out.println(v.toString());
        }
    }

    /**
     * 'go' produces a useful set of output that allows you to see the
     * initial state of the modules arraylist, the results of doing the
     * tree walk, an attempt at finding an object in the tree and
     * the results of doing a sort and a search. Initially,
     * only the state of the modules list will be correct. As you add
     * further functionality, you should see the correct output being
     * printed via this method. You do not need to modify it but you may
     * want to add your own checks to confirm your code is working as intended.
     */
    public void go() {
        loadData();
        System.out.println("--Module List--");
        stirling.describeModuleList();
        System.out.println();

        System.out.println("\n--Tree Walk--\n" + stirling.walkTree(5, "inOrder"));
        System.out.println("\n--Tree Walk--\n" + stirling.walkTree(5, "custom"));

        System.out.println("\n--Find--");
        Student mf = stirling.find(0, "John J"); // should be found
        if (mf != null)
            System.out.println("Found: " + mf);
        else
            System.out.println("Could not find John J");

        Student aw = stirling.find(1, "Jack J"); // should not be found
        if (aw != null)
            System.out.println("Found: " + aw);
        else
            System.out.println("Could not find Jack J");

        System.out.println("\n--Searching module name--");
        // Reload the data, otherwise it will still be sorted...
        loadData();
        Module s = stirling.searchForModule("Natural Language Processing");
        if (s != null)
            System.out.println("Found module: " + s);
        else
            System.out.println("Could not find module");
        Module sw = stirling.searchForModule("Introduction to Data Science");
        if (sw != null)
            System.out.println("Found module: " + sw);
        else
            System.out.println("Could not find module");

        System.out.println("\n--Sort ascending name--");
        ArrayList<Cohort> sorted;
        // Reload the data, otherwise it will still be sorted...
        loadData();
        sorted = stirling.sortMethod(true, "name");
        printArrayList(sorted);

        System.out.println("\n--Sort descending code--");
        // Reload the data, otherwise it will still be sorted...
        loadData();
        sorted = stirling.sortMethod(false, "code");
        printArrayList(sorted);

    }

    /**
     * A test for the tree walk
     */
    @Test
    public void walkTest() {
        loadData();

        // Test multiple cohort walks
        for (int i = 0; i < 3; i++) {
            String result = stirling.walkTree(i, "inOrder");
            assertNotNull(result);
            System.out.println("Cohort " + i + ": " + result);
        }
    }

    /**
     * A test for the graph loop
     */
    @Test
    public void loopSearchTest() {
        loadData();

        // Original graph contains cycles
        assertTrue(stirling.checkForCycles(), "Complex module graph has cycles");

        // Create linear dependency test
        University testUni = new University();
        Professor prof = new Professor("Test", 1, "t@uni.ac.uk", "CSM", 1.0);

        Module[] modules = {
                new Module(1, "Start"),
                new Module(2, "Middle"),
                new Module(3, "End")
        };

        // Linear: Start → Middle → End
        modules[1].addPrerequisites(modules[0]);
        modules[2].addPrerequisites(modules[1]);

        for (Module m : modules) {
            testUni.addClass(new Cohort(m, new BinaryTree(), prof));
        }

        // No cycles in linear chain
        Assertions.assertFalse(testUni.checkForCycles(), "Straight path has no cycles");
    }

    /**
     * A test for the tree find method
     */
    @Test
    public void findTest() {
        loadData();

        // Search for student "John J" in cohort 0
        Student found = stirling.find(0, "John J");
        assertNotNull(found);

        // Search for non-existent student
        Student notFound = stirling.find(0, "Ghost Student");
        assertNull(notFound);

    }

    /**
     * A test for the tree find method, demonstrating a bug!
     */
    @Test
    public void findTestBugDemo() {
        stirling.getCohort().get(0).getBtree().printTree(); // Shows that Loius L is in the tree
        assertNotNull(stirling.getCohort().get(0).getBtree().find("Loius L")); // not found!
    }

    /**
     * A test for the search method
     */
    @Test
    public void searchTest() {
        loadData();

        // Search for module "Introduction to Data Science"
        Module found = stirling.searchForModule("Introduction to Data Science");
        assertNotNull(found);

        // Search for non-existent module
        Module notFound = stirling.searchForModule("Fake Module");
        assertNull(notFound);
    }


    /**
     * A test for the sorting method
     */


    @Test
    public void sortingTest() {

        // Test sorting by name in increasing order
        loadData();
        ArrayList<Cohort> nameAscResult = stirling.sortMethod(true, "name");
        assertTrue(verifyNameOrder(nameAscResult, true),
                "Module names should be in alphabetical order");

        // Test sorting by name in decreasing order
        loadData();
        ArrayList<Cohort> nameDescResult = stirling.sortMethod(false, "name");
        assertTrue(verifyNameOrder(nameDescResult, false),
                "Module names should be in reverse alphabetical order");

        // Test sorting by module code from low to high
        loadData();
        ArrayList<Cohort> codeAscResult = stirling.sortMethod(true, "code");
        assertTrue(verifyCodeOrder(codeAscResult, true),
                "Module codes should be in ascending order");

        // Test sorting by module code from high to low
        loadData();
        ArrayList<Cohort> codeDescResult = stirling.sortMethod(false, "code");
        assertTrue(verifyCodeOrder(codeDescResult, false),
                "Module codes should be in descending order");

        // Check edge case: List with only one element
        University singleItem = new University();
        Professor prof = new Professor("Sample", 1, "sample@stir.ac.uk", "CSM", 1.0);
        singleItem.addClass(new Cohort(new Module(999, "Only Module"), new BinaryTree(), prof));

        ArrayList<Cohort> singleSorted = singleItem.sortMethod(true, "name");
        assertEquals(1, singleSorted.size(), "Single element list size should be 1");
    }

    // Helper to validate name ordering
    private boolean verifyNameOrder(ArrayList<Cohort> cohorts, boolean increasing) {
        for (int i = 0; i < cohorts.size() - 1; i++) {
            String currentName = cohorts.get(i).getModule().getName();
            String nextName = cohorts.get(i + 1).getModule().getName();

            int comparison = currentName.compareTo(nextName);

            if (increasing && comparison > 0) return false;
            if (!increasing && comparison < 0) return false;
        }
        return true;
    }

    // Helper to validate code ordering
    private boolean verifyCodeOrder(ArrayList<Cohort> cohorts, boolean increasing) {
        for (int i = 0; i < cohorts.size() - 1; i++) {
            int currentCode = cohorts.get(i).getModule().getCode();
            int nextCode = cohorts.get(i + 1).getModule().getCode();

            if (increasing && currentCode > nextCode) return false;
            if (!increasing && currentCode < nextCode) return false;
        }
        return true;

    }

    /**
     * A test for the sorting method
     */
    @Test
    public void sortingSpeedTest() {
        int[] sizes = {100, 500, 1000, 5000, 10000};

        System.out.println("\n=== QuickSort Time Analysis ===");
        System.out.println("Using nanoTime() for precision, converting to milliseconds");
        System.out.println("=========================================================");
        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "Size(n)", "Time(ms)", "n log n", "Ratio");
        System.out.println("---------------------------------------------------------");

        Random r = new Random(42); // Fixed seed for consistency

        for (int size : sizes) {
            // Create test university
            University uni = new University();
            Professor prof = new Professor("Prof", 1, "p@test.com", "CSM", 1.0);

            // Add random modules
            for (int i = 0; i < size; i++) {
                Module m = new Module(i, "Course" + r.nextInt(10000));
                uni.addClass(new Cohort(m, new BinaryTree(), prof));
            }

            // Measure with nanoTime, convert to milliseconds
            long startNano = System.nanoTime();
            uni.sortMethod(true, "name");
            long endNano = System.nanoTime();

            // Convert nanoseconds to milliseconds (1 ms = 1,000,000 ns)
            double timeMs = (endNano - startNano) / 1_000_000.0;

            // Calculate n log n
            double nLogN = size * (Math.log(size) / Math.log(2));
            double ratio = timeMs / nLogN;

            System.out.printf("%-10d %-15.3f %-15.0f %-15.6f%n",
                    size, timeMs, nLogN, ratio);
        }
    }

        /**
         * A test for the module enrolment
         */
        @Test
        public void checkModuleEnrolment () {
            Student s = new Student("Sam", 999, "sam@stir.ac.uk");

            // Create modules
            Module[] modules = new Module[6];
            for (int i = 0; i < 6; i++) {
                modules[i] = new Module(i + 100, "Course" + i);
            }

            // Add first 5 modules (should work)
            for (int i = 0; i < 5; i++) {
                assertTrue(s.addModule(modules[i]));
            }

            // Try 6th module (should fail - max reached)
            Assertions.assertFalse(s.addModule(modules[5]));

            // Try duplicate (should fail)
            Assertions.assertFalse(s.addModule(modules[0]));

            // Remove one module
            assertTrue(s.removeModule(modules[0]));

            // Add the 6th module now (should work)
            assertTrue(s.addModule(modules[5]));
        }
    }


