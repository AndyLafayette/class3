import java.util.*;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class PageRankReducer
extends Reducer<Text, Text, Text, Text> {
@Override
public void reduce(Text key, Iterable<Text> values, Context context)
throws IOException, InterruptedException {
double PR = 0.0; 
String val = null; 
for (Text value : values) {
String temp = value.toString();
if (temp.charAt(temp.length() - 1) < '0' || temp.charAt(temp.length() - 1) > '9') {
	val = temp;
	continue;
};
String[] strs = temp.split(" ");
PR += Double.parseDouble(strs[1]);
}
context.write(key, new Text(val + " " + PR));
}
}