package com.tutorial.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;

public class TreeNode<N extends Tree.Node<N,String, Image>> extends Tree.Node<N,String, Image>{

    public TreeNode(Image actor){
        super(actor);
    }

    @Override
    public String getValue() {
        return super.getValue();
    }

    public void add(TreeNode<?> secondNode) {
        super.add((N) secondNode);
    }
}
