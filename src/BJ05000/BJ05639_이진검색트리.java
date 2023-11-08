package BJ05000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ05639_이진검색트리 {
    private static class Node {
        public Node(long value) {
            this.value = value; this.left = this.right = null;
        }

        long value;
        Node left, right;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        Node root = null; String num;
        while ((num = br.readLine()) != null && !num.isEmpty()) {
            root = insert(root, Long.parseLong(num));
        }

        print(root);

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static Node insert(Node root, long value) {
        if(root == null) {
            return new Node(value);
        }

        if(value < root.value) {  // 왼쪽 노드일 경우
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    private static void print(Node root) {
        if(root.left != null) print(root.left);
        if(root.right != null) print(root.right);
        sb.append(root.value).append("\n");

    }
}

