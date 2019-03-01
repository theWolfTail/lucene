package lucene.chapter2;
import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class StdAnalyzer {
	private static String strCh = "�л����񹲺͹�����й���ʱһ����12���˿ڵĹ���";	
	private static String strEn = "Dogs can not achieve a place, eyes can reach";
	public static void stdAnalyzer(String str) throws IOException{
		/**
		 * �����������������ǹ̶��ģ���ס�ú���������ݼ���
		 */
		Analyzer analyzer = null;
		analyzer = new StandardAnalyzer();
		TokenStream toStream = analyzer.tokenStream(str, new StringReader(str));		//��һ���ַ���������token��
		toStream.reset();				//�����
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);		//������Ӧ�ʻ�
		System.out.println("�ִʽ����");
		while(toStream.incrementToken()){
			System.out.print(teAttribute.toString()+"|");
		}
		System.out.print("\n");
		analyzer.close();
	}
	public static void main(String[] args) throws IOException {
		System.out.println("�����ķִʣ�");
		stdAnalyzer(strCh);
		System.out.println("��Ӣ�ķִ�");
		stdAnalyzer(strEn);
	}
}
