public class LogUtil {

    public static <T> String printPayload(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder("[");
        Object object;
        if (list != null) {
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                object = iter.next();
                stringBuilder.append(object.toString() + ",");
            }
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static <T> String printPayload(Object object) {
        return "[" + (object == null ? "null" : object.toString()) + "]";
    }
}
