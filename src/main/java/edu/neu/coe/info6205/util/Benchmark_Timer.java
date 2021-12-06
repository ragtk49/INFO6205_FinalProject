package edu.neu.coe.info6205.util;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import edu.neu.coe.info6205.sort.counting.*;
import edu.neu.coe.info6205.sort.counting.TimSort;
import edu.neu.coe.info6205.sort.huskySort.sort.huskySort.MergeHuskySort;
import edu.neu.coe.info6205.sort.huskySort.sort.huskySort.PureHuskySort;



/**
 * This class implements a simple Benchmark utility for measuring the running time of algorithms.
 * It is part of the repository for the INFO6205 class, taught by Prof. Robin Hillyard
 * <p>
 * It requires Java 8 as it uses function types, in particular, UnaryOperator&lt;T&gt; (a function of T => T),
 * Consumer&lt;T&gt; (essentially a function of T => Void) and Supplier&lt;T&gt; (essentially a function of Void => T).
 * <p>
 * In general, the benchmark class handles three phases of a "run:"
 * <ol>
 *     <li>The pre-function which prepares the input to the study function (field fPre) (may be null);</li>
 *     <li>The study function itself (field fRun) -- assumed to be a mutating function since it does not return a result;</li>
 *     <li>The post-function which cleans up and/or checks the results of the study function (field fPost) (may be null).</li>
 * </ol>
 * <p>
 * Note that the clock does not run during invocations of the pre-function and the post-function (if any).
 *
 * @param <T> The generic type T is that of the input to the function f which you will pass in to the constructor.
 */
public class Benchmark_Timer<T> implements Benchmark<T> {

    /**
     * Calculate the appropriate number of warmup runs.
     *
     * @param m the number of runs.
     * @return at least 2 and at most m/10.
     */
    static int getWarmupRuns(int m) {
        return Integer.max(2, Integer.min(10, m / 10));
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    @Override
    public double runFromSupplier(Supplier<T> supplier, int m) {
        // Warmup phase
        final Function<T, T> function = t -> {
            fRun.accept(t);
            return t;
        };
        new Timer().repeat(getWarmupRuns(m), supplier, function, fPre, null);

        // Timed phase
        return new Timer().repeat(m, supplier, function, fPre, fPost);
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three functions.
     *
     * @param description the description of the benchmark.
     * @param fPre        a function of T => T.
     *                    Function fPre is run before each invocation of fRun (but with the clock stopped).
     *                    The result of fPre (if any) is passed to fRun.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *                    When you create a lambda defining fRun, you must return "null."
     * @param fPost       a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun, Consumer<T> fPost) {
        this.description = description;
        this.fPre = fPre;
        this.fRun = fRun;
        this.fPost = fPost;
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three functions.
     *
     * @param description the description of the benchmark.
     * @param fPre        a function of T => T.
     *                    Function fPre is run before each invocation of fRun (but with the clock stopped).
     *                    The result of fPre (if any) is passed to fRun.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun) {
        this(description, fPre, fRun, null);
    }

    /**
     * Constructor for a Benchmark_Timer with only fRun and fPost Consumer parameters.
     *
     * @param description the description of the benchmark.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *                    When you create a lambda defining fRun, you must return "null."
     * @param fPost       a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, Consumer<T> fRun, Consumer<T> fPost) {
        this(description, null, fRun, fPost);
    }
    
    public static String extractName(String[] words){
        String word = words[0];
        return word;
    }
    
    public static List<String> getPOrdered(List<String> hindiWords){
    	List<String> result=new ArrayList<>();
    	List<String> result1=new ArrayList<>();
    	List<String> result2=new ArrayList<>();
    	for(int i=0;i<hindiWords.size()/2;i++) {
    		result1.add(hindiWords.get(i));
    	}
    	Collections.sort(result1);
    	for(int i=hindiWords.size()/2;i<hindiWords.size();i++) {
    		result2.add(hindiWords.get(i));
    	}
    	for(String s:result1) {
    		result.add(s);
    	}
    	for(String s:result2) {
    		result.add(s);
    	}
    	return result;
    }

    /**
     * Constructor for a Benchmark_Timer where only the (timed) run function is specified.
     *
     * @param description the description of the benchmark.
     * @param f           a Consumer function (i.e. a function of T => Void).
     *                    Function f is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     */
    public Benchmark_Timer(String description, Consumer<T> f) {
        this(description, null, f, null);
    }

    private final String description;
    private final UnaryOperator<T> fPre;
    private final Consumer<T> fRun;
    private final Consumer<T> fPost;
    
    public static void main(String[] args) throws IOException {
        
        final String[] hindiWords =FileUtil.hindiWordsList("/Users/chandrakanthyadav/Downloads/hindiText_250k.txt");
        
        /**
         * Benchmarking of MSD
         */
        System.out.println("Benchmarking the randomly ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
            MSD msd=new MSD();
            Consumer<String[]> consumer = (ar) -> {
            	msd.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Benchmarking the reverse ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	MSD msd=new MSD();
        	Consumer<String[]> consumer = (ar) -> {
            	msd.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Benchmarking the partially ordered array ");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
        	MSD msd=new MSD();
            Consumer<String[]> consumer = (ar) -> {
            	msd.sort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        
        /**
         * Benchmarking of LSD
         */
        System.out.println("Benchmarking the randomly ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
            LSD lsd=new LSD();
            Consumer<String[]> consumer = (ar) -> {
            	lsd.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Benchmarking the reverse ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	LSD lsd=new LSD();
        	Consumer<String[]> consumer = (ar) -> {
            	lsd.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Benchmarking the partially ordered array for atleast 6 values of n");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
        	LSD lsd=new LSD();
            Consumer<String[]> consumer = (ar) -> {
            	lsd.sort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        /**
         * Benchmarking of Quick Dual Pivot
         */
        System.out.println("Benchmarking the randomly ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
            Consumer<String[]> consumer = (ar) -> {
            	QuickDualPivot.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Benchmarking the reverse ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	Consumer<String[]> consumer = (ar) -> {
        		QuickDualPivot.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Benchmarking the partially ordered array for atleast 6 values of n");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
            Consumer<String[]> consumer = (ar) -> {
            	QuickDualPivot.sort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        
        /**
         * Benchmarking Husky Sort
         */
        System.out.println("Benchmarking the randomly ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	HuskySortImplementation hsp=new HuskySortImplementation();
            Consumer<String[]> consumer = (ar) -> {
            	hsp.mergeSort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        for(int i=200;i<=7000;i*=2) {
        	HuskySortImplementation hsp=new HuskySortImplementation();
            Consumer<String[]> consumer = (ar) -> {
            	hsp.pureSort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Benchmarking the reverse ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	HuskySortImplementation hsp=new HuskySortImplementation();
        	Consumer<String[]> consumer = (ar) -> {
        		hsp.mergeSort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	HuskySortImplementation hsp=new HuskySortImplementation();
        	Consumer<String[]> consumer = (ar) -> {
        		hsp.pureSort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Benchmarking the partially ordered array for atleast 6 values of n");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
        	HuskySortImplementation hsp=new HuskySortImplementation();
            Consumer<String[]> consumer = (ar) -> {
            	hsp.mergeSort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
        	HuskySortImplementation hsp=new HuskySortImplementation();
            Consumer<String[]> consumer = (ar) -> {
            	hsp.pureSort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        /**
         * Benchmarking Tim Sort
         */
        System.out.println("Benchmarking the randomly ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
            TimSort ts=new TimSort();
            Consumer<String[]> consumer = (ar) -> {
            	ts.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Benchmarking the reverse ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	Collections.sort(Arrays.asList(hindiWords));
        	Collections.reverse(Arrays.asList(hindiWords));
        	TimSort ts=new TimSort();
        	Consumer<String[]> consumer = (ar) -> {
        		ts.sort(hindiWords);
            };
            consumer.accept(hindiWords);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + hindiWords.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(hindiWords, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Benchmarking the partially ordered array");
        System.out.println();
        for(int i=200;i<=7000;i*=2) {
        	List<String> wordsList=getPOrdered(Arrays.asList(hindiWords));
        	String[] wordsArr=(String[]) wordsList.toArray();
        	TimSort ts=new TimSort();
            Consumer<String[]> consumer = (ar) -> {
            	ts.sort(wordsArr);
            };
            consumer.accept(wordsArr);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<>("Benchmarking sort(Insertion) function for array with random elements of length : " + wordsArr.length, consumer);
            System.out.println("Time : "+benchmarkTimer.run(wordsArr, 1));
            
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
    }
}
