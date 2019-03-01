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
	private static String strCn = "�л����񹲺͹�����й�����һ����12���˿ڵĹ���";
	private static String strEn = "Dogs can not achieve a place, eyes can reach";
	public static void printAnalyzer(String str, Analyzer analyzer)throws IOException{
		TokenStream toStream = analyzer.tokenStream(str, new StringReader(str));
		toStream.reset();
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
		System.out.println("�ִʽ����");
		while(toStream.incrementToken()){
			System.out.print(teAttribute.toString()+"|");
		}
		System.out.println("\n");
		analyzer.close();	
	}
	public static void main(String[] args) throws IOException{
		Analyzer analyzer = null;
		analyzer = new StandardAnalyzer();
		System.out.println("��׼�ִʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new CJKAnalyzer();
		System.out.println("���ִַʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new WhitespaceAnalyzer();
		System.out.println("�ո�ִʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new KeywordAnalyzer();
		System.out.println("�ؼ��ִַʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new StopAnalyzer();
		System.out.println("ͣ���ִַʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new SimpleAnalyzer();
		System.out.println("�򵥷ִʣ�");
		printAnalyzer(strCn, analyzer);
		analyzer = new SmartChineseAnalyzer();
		System.out.println("�������ִܷʣ�");
		printAnalyzer(strCn, analyzer);
	}
}
