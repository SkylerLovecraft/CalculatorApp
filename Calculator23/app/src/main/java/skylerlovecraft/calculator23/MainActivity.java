package skylerlovecraft.calculator23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{

    Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine, buttonZero;
    Button buttonEquals, buttonAns, buttonMultiply, buttonSubtract, buttonAdd, buttonDivide,
            buttonClear, buttonBackspace, buttonDecimal, buttonPlusMinus;

    TextView tvResult;
    double previousAnswer;
    String lastChar;
    String postfixString;
    String userEquation, systemEquation;
    Boolean decimalFlag;
    Boolean answerAvailable;
    Boolean answerDisplayed;
    ShuntingYard sy;
    PostfixCalculator postfixCalculator;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.previousAnswer = 0.0;
        this.lastChar = null;
        this.decimalFlag = false;
        this.answerDisplayed = false;
        this.result = 0.0f;
        this.answerAvailable = false;
        this.sy = new ShuntingYard();
        this.postfixCalculator = new PostfixCalculator();
        this.systemEquation = "0";
        this.userEquation = "0";
        //clearEquation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewComponents();
    }

    void initializeViewComponents()
    {
        this.buttonOne       = (Button)findViewById(R.id.buttonOne);
        this.buttonTwo       = (Button)findViewById(R.id.buttonTwo);
        this.buttonThree     = (Button)findViewById(R.id.buttonThree);
        this.buttonFour      = (Button)findViewById(R.id.buttonFour);
        this.buttonFive      = (Button)findViewById(R.id.buttonFive);
        this.buttonSix       = (Button)findViewById(R.id.buttonSix);
        this.buttonSeven     = (Button)findViewById(R.id.buttonSeven);
        this.buttonEight     = (Button)findViewById(R.id.buttonEight);
        this.buttonNine      = (Button)findViewById(R.id.buttonNine);
        this.buttonZero      = (Button)findViewById(R.id.buttonZero);

        this.buttonEquals    = (Button)findViewById(R.id.buttonEquals);
        this.buttonAns       = (Button)findViewById(R.id.buttonAns);
        this.buttonMultiply   = (Button)findViewById(R.id.buttonMultiply);
        this.buttonSubtract  = (Button)findViewById(R.id.buttonSubtract);
        this.buttonAdd       = (Button)findViewById(R.id.buttonAdd);
        this.buttonDivide    = (Button)findViewById(R.id.buttonDivide);
        this.buttonClear     = (Button)findViewById(R.id.buttonClear);
        this.buttonBackspace = (Button)findViewById(R.id.buttonBackspace);
        this.buttonDecimal   = (Button)findViewById(R.id.buttonDecimal);
        this.buttonPlusMinus = (Button)findViewById(R.id.buttonPlusMinus);

        this.tvResult        = (TextView)findViewById(R.id.textView);

        initializeClickListeners();
    }

    void initializeClickListeners()
    {
        buttonOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("1");
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("2");
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("3");
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("4");
            }
        });
        buttonFive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("5");
            }
        });
        buttonSix.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("6");
            }
        });
        buttonSeven.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("7");
            }
        });
        buttonEight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("8");
            }
        });
        buttonNine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("9");
            }
        });
        buttonZero.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation("0");
            }
        });
        buttonAns.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                double temp = 0.0;
                if(answerAvailable == true) {
                    if(previousAnswer < 0.0) {
                        writeToEquation("(-)");
                        temp = previousAnswer * -1;
                    }
                    else
                        temp = previousAnswer;
                    if(previousAnswer - Math.floor(previousAnswer) == 0)
                        writeToEquation(String.valueOf((int)temp));
                    else
                        writeToEquation(String.valueOf(temp));
                }
                else
                    return;
            }
        });
        buttonDecimal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkLastChar();
                if(decimalFlag == true)
                    return;
                else {
                    writeToEquation(".");
                    decimalFlag = true;
                }
            }
        });
        buttonEquals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkLastChar())
                    return;
                else
                    computeEquation();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkLastChar())
                    return;
                else {
                    writeToEquation((" "));
                    writeToEquation(("+"));
                    writeToEquation((" "));
                }
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkLastChar())
                    return;
                else {
                    writeToEquation((" "));
                    writeToEquation(("-"));
                    writeToEquation((" "));

                }
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkLastChar())
                    return;
                else {
                    writeToEquation((" "));
                    writeToEquation(("*"));
                    writeToEquation((" "));
                }
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkLastChar())
                    return;
                else {
                    writeToEquation((" "));
                    writeToEquation(("/"));
                    writeToEquation((" "));
                }
            }
        });
        buttonBackspace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String temp = tvResult.getText().toString();
                int length = temp.length();
                if(length > 1) {
                    temp = temp.substring(0, length - 1);
                    tvResult.setText(temp);
                    userEquation = temp;
                    if(systemEquation.charAt(systemEquation.length() - 1) == ' ')
                    {
                        System.out.println("length-2");
                        systemEquation = systemEquation.substring(0, systemEquation.length() - 3);

                    }
                    else
                    {
                        systemEquation = systemEquation.substring(0, systemEquation.length() -1);
                        System.out.println("in length");
                    }

                }
                else
                    clearEquation();
                System.out.println("user: " +userEquation);
                System.out.println("syste: " +systemEquation);

            }
        });
        buttonPlusMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                writeToEquation(" ");
                writeToEquation("(");
                writeToEquation("-");
                writeToEquation(")");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearEquation();
            }
        });
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeToEquation(String s)
    {
        String temp = tvResult.getText().toString();

        if(temp.compareTo("0") == 0 || answerDisplayed == true)
        {
            answerDisplayed = false;
            System.out.println("in the temp etc");
            userEquation = s;
            systemEquation = s;
            tvResult.setText(s);
        }
        else
        {
            if(s.compareTo(" ") == 0)
            {
                systemEquation = systemEquation.concat(s);
            }
            else
            {
                systemEquation = systemEquation.concat(s);
                userEquation = userEquation.concat(s);
            }
            tvResult.setText(userEquation);
        }
        if(s != " ")
            lastChar = s;

    }

    public void clearEquation()
    {
        System.out.println("in clear");
        this.userEquation = "0";
        this.systemEquation = "0";
        tvResult.setText(userEquation);
    }

    public boolean checkLastChar(){
        if(lastChar == "+" || lastChar == "-" || lastChar == "*" || lastChar == "/")
        {
            decimalFlag = false;
            return true;
        }
        if(lastChar == ".")
        {
            decimalFlag = true;
            return true;
        }
        if(userEquation.length() == 1 && userEquation.charAt(0) == '0')
            return true;
        return false;
    }
    public void computeEquation()
    {
        System.out.println("userEquation: " + userEquation);
        System.out.println("sytemEquation: " + systemEquation);
        postfixString = sy.infixToPostfix(systemEquation);
        System.out.println("postfixString: " + postfixString);
        double result = postfixCalculator.evaluate(postfixString);
        this.previousAnswer = result;
        //cThis will display the final result either as an integer or a decimal format number.
        //
        if(result == Math.floor(result) && result < 2147483647)
        {
            int x = (int)result;
            String str = Integer.toString(x);
            tvResult.setText(str);
        }
        else {
            //Because we are using floats which follow IEEE 754, they will lose some precision and
            //We need to round to the
            tvResult.setText((Double.toString(result)));
        }
        this.decimalFlag = false;
        this.answerAvailable = true;
        this.answerDisplayed = true;

    }
}