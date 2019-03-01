package lucene.chapter2;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class StdAnalyzer {
	private static String strCh = "中华人民共和国简称中国，时一个有12亿人口的国家";	
	private static String strEn = "Dogs can not achieve a place, eyes can reach";
	public static void stdAnalyzer(String str) throws IOException{
		/**
		 * 这个函数里面的内容是固定的，记住该函数里的内容即可
		 */
		Analyzer analyzer = null;
		analyzer = new StandardAnalyzer();
		TokenStream toStream = analyzer.tokenStream(str, new StringReader(str));		//将一个字符串创建成token流
		toStream.reset();				//清空流
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);		//保存相应词汇
		System.out.println("分词结果：");
		while(toStream.incrementToken()){
			System.out.print(teAttribute.toString()+"|");
		}
		System.out.print("\n");
		analyzer.close();
	}
	public static void main(String[] args) throws IOException {
		System.out.println("对中文分词：");
		stdAnalyzer(strCh);
		System.out.println("对英文分词");
		stdAnalyzer(strEn);
	}
}
