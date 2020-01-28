package  changeJarPack ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Write a description of class ChangeJarPanel here.
 *
 * @author Roger Ferguson
 */
public class ChangeJarPanel extends JPanel{

    private ChangeJar jar;

    NumberFormat fmt = NumberFormat.getCurrencyInstance();
    JButton takeOutButton, mutateButton, equalsButton, compareToButton,
            decreaseButton, increaseButton, addButton, toStringButton, changeJarButton,
            saveButton, loadButton;
    JTextField qField, dField, nField, pField, fField;

    /** labels for message and credits */
    JLabel message, credits;

    public ChangeJarPanel(){

        // create the game object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(100,2,3,4);

        // set the layout to GridBag
        setLayout(new GridLayout(6,2));
        setBackground(Color.lightGray);


        qField = new JTextField("0", 3);
        add(qField);
        add(new JLabel("Quarters:"));

        dField = new JTextField("0", 3);
        add(dField);
        add(new JLabel("Dimes:"));

        nField = new JTextField("0", 3);
        add(nField);
        add(new JLabel("Nickels:"));

        pField = new JTextField("0", 3);
        add(pField);
        add(new JLabel("Pennies:"));

        fField = new JTextField("", 3);
        add(fField);
        add(new JLabel("File Name:"));

        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        add (new JLabel(" "));
        add(new JLabel("Total:"));
        add(credits);

        takeOutButton = new JButton("Take Out");
        add(takeOutButton);

        changeJarButton = new JButton("New Change Jar");
        add(changeJarButton);

        mutateButton = new JButton("Mutate");
        add(mutateButton);

        equalsButton = new JButton("Equals");
        add(equalsButton);

        compareToButton = new JButton("Compare To");
        add(compareToButton);

        decreaseButton = new JButton("Decrease");
        add(decreaseButton);

        increaseButton = new JButton("Increase");
        add(increaseButton);

        addButton = new JButton("Add");
        add(addButton);

        toStringButton = new JButton("Print");
        add(toStringButton);

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        // register the listeners
        takeOutButton.addActionListener(new ButtonListener());
        mutateButton.addActionListener(new ButtonListener());
        equalsButton.addActionListener(new ButtonListener());
        compareToButton.addActionListener(new ButtonListener());
        decreaseButton.addActionListener(new ButtonListener());
        increaseButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        toStringButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
    }


    /****************************************************************
     Inner class to repond to the user action

     ****************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            int quarters, dimes, nickels, pennies;
            String file;

            if (event.getSource() == takeOutButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.takeout(quarters,dimes,nickels,pennies);
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == mutateButton){
                if (ChangeJar.MUTATE) {
                    ChangeJar.mutate(false);
                }
                else{
                    ChangeJar.mutate(true);
                }
                JOptionPane.showMessageDialog(null, "Mutation is " + ChangeJar.MUTATE);
            }
            if (event.getSource() == changeJarButton){
                try{
                    if (qField.getText().equals("")) {
                        jar = new ChangeJar();
                    }
                    else{
                        jar = new ChangeJar(Double.parseDouble(qField.getText()));
                    }
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == equalsButton){
                quarters = Integer.parseInt(qField.getText());
                dimes = Integer.parseInt(dField.getText());
                nickels = Integer.parseInt(nField.getText());
                pennies = Integer.parseInt(pField.getText());
                ChangeJar other = new ChangeJar(quarters,dimes,nickels,pennies);
                if(!jar.equals(other)){
                    JOptionPane.showMessageDialog(null,"These 2 ChangeJars are not the same");
                }
                if(jar.equals(other)){
                    JOptionPane.showMessageDialog(null,"These 2 ChangeJars are the same");
                }

            }
            if (event.getSource() == compareToButton){
                quarters = Integer.parseInt(qField.getText());
                dimes = Integer.parseInt(dField.getText());
                nickels = Integer.parseInt(nField.getText());
                pennies = Integer.parseInt(pField.getText());
                ChangeJar other = new ChangeJar(quarters,dimes,nickels,pennies);
                jar.compareTo(other);
                if(jar.compareTo(other)==0){
                    JOptionPane.showMessageDialog(null,"These 2 ChangeJars are the same");
                }
                if(jar.compareTo(other)==1){
                    JOptionPane.showMessageDialog(null,"The new ChangeJar is less than the Original ChangeJar");
                }
                if(jar.compareTo(other)==-1){
                    JOptionPane.showMessageDialog(null,"The new ChangeJar is greater than than the Original ChangeJar");
                }

            }
            if (event.getSource() == decreaseButton){
                try{
                    jar.dec();
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == increaseButton){
                try {
                    jar.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.getSource() == addButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.add(quarters,dimes,nickels,pennies);
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == toStringButton){
                try{
                    System.out.println(jar.toString());
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == saveButton){
                try{
                    file = fField.getText();
                    jar.save(file);
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Something went wrong");
                }
            }
            if (event.getSource() == loadButton){
                try{
                    file = fField.getText();
                    jar.load(file);
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Something went wrong");
                }
            }
            // update the labels
            credits.setText(fmt.format(jar.getAmount()));
        }

    }
}
