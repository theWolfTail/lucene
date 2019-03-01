package lucene.chapter2;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


public class AllAanlyzer {
	private static String strCn = "中华人民共和国简称中国，是一个有12亿人口的国家";
	private static String strEn = "Dogs can not achieve a place, eyes can reach";
	public static void printAnalyzer(String str, Analyzer analyzer)throws IOException{
		TokenStream toStream = analyzer.tokenStream(str, new StringReader(str));
		toStream.reset();
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
		System.out.println("分词结果：");
		while(toStream.incrementToken()){
			System.out.print(teAttribute.toString()+"|");
		}
		System.out.println("\n");
		analyzer.close();	
	}
	public static void main(String[] args) throws IOException{
		Analyzer analyzer = null;
		analyzer = new StandardAnalyzer();
		System.out.println("标准分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new CJKAnalyzer();
		System.out.println("二分分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new WhitespaceAnalyzer();
		System.out.println("空格分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new KeywordAnalyzer();
		System.out.println("关键字分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new StopAnalyzer();
		System.out.println("停用字分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new SimpleAnalyzer();
		System.out.println("简单分词：");
		printAnalyzer(strCn, analyzer);
		analyzer = new SmartChineseAnalyzer();
		System.out.println("中文智能分词：");
		printAnalyzer(strCn, analyzer);
	}
}
