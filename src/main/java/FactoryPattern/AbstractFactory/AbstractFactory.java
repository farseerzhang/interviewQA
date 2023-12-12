package FactoryPattern.AbstractFactory;

import FactoryPattern.AbstractFactory.Product.Color;
import FactoryPattern.AbstractFactory.Product.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
