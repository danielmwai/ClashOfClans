package com.bw.looters.ms.web.vo.req;

import com.bw.looters.ms.web.vo.Res;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhYou
 */
public class TreeNodesRes extends Res {

	Set<TreeNode> nodes;

	public Set<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(Set<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public static class TreeNode implements Comparable<TreeNode> {

		private int id;
		private String text;
		private boolean leaf;

		private Set<TreeNode> children;

		public TreeNode() {
		}

		public TreeNode(int id, String text, boolean leaf) {
			this.id = id;
			this.text = text;
			this.leaf = leaf;
		}

		public TreeNode addChildren(TreeNode node) {
			if (children == null) {
				children = new TreeSet<TreeNode>();
			}
			children.add(node);
			return this;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		public Set<TreeNode> getChildren() {
			return children;
		}

		public void setChildren(Set<TreeNode> children) {
			this.children = children;
		}

		public int compareTo(TreeNode o) {
			return this.getId() - o.getId();
		}
	}
}
