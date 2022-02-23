package com.example.structure.support;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import androidx.annotation.Nullable;

/**
 * Created by Rajesh Pradeep G on 13-12-2019
 */
public class CustomInputEditText extends LinearLayout {

    private TextInputLayout inputLayout;
    private TextInputEditText inputEditText;
    private LinearLayout bgHolder;
    private TextView errorMessage;

    public CustomInputEditText(Context context) {
        super(context);
        this.initView(context, (AttributeSet) null);
    }

    public CustomInputEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    private void initView(Context mContext, AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CustomInputEditText, 0, 0);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_text_layout, this, true);
        this.inputLayout = this.findViewById(R.id.textInputLayout);
        this.inputEditText = this.findViewById(R.id.textInput);
        this.bgHolder = this.findViewById(R.id.bgHolder);
        this.errorMessage = this.findViewById(R.id.errorMessage);
        Drawable left = a.getDrawable(R.styleable.CustomInputEditText_leftIcon);
        Drawable right = a.getDrawable(R.styleable.CustomInputEditText_rightIcon);
        Drawable top = a.getDrawable(R.styleable.CustomInputEditText_topIcon);
        Drawable bottom = a.getDrawable(R.styleable.CustomInputEditText_bottomIcon);
        String errMessage = a.getString(R.styleable.CustomInputEditText_errorMessage);
        String hintMessage = a.getString(R.styleable.CustomInputEditText_inputHint);
        int InputTypes = a.getInt(R.styleable.CustomInputEditText_android_inputType, 0);
        int maxLines = a.getInt(R.styleable.CustomInputEditText_inputMaxLines, 0);
        int lines = a.getInt(R.styleable.CustomInputEditText_inputLines, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.inputEditText.setTextColor(ColorStateList.valueOf(mContext.getColor(R.color.hint_color)));
        }
        if (a != null) {
            if (errMessage != null) {
                this.errorMessage.setText(errMessage);
            }

            if (hintMessage != null) {
                this.inputLayout.setHintEnabled(true);
                this.inputLayout.setHint(hintMessage);
            }

            if (a.getInteger(R.styleable.CustomInputEditText_inputEmsMax, 0) != 0) {
                this.inputEditText.setMaxEms(a.getInt(R.styleable.CustomInputEditText_inputEmsMax, 0));
            } else if (a.getInteger(R.styleable.CustomInputEditText_inputEms, 0) != 0) {
                this.inputEditText.setEms(a.getInteger(R.styleable.CustomInputEditText_inputEms, 0));
            }

            this.inputEditText.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
//            this.inputEditText.setCompoundDrawablePadding(10);
            if (a.getBoolean(R.styleable.CustomInputEditText_isError, false)) {
                this.errorMessage.setVisibility(VISIBLE);
            } else {
                this.errorMessage.setVisibility(GONE);
            }

            if (InputTypes != 0) {
                this.inputEditText.setInputType(InputTypes);
                this.inputEditText.setPaddingRelative(0,10,0,0);
            }

            if (maxLines != 0) {
                this.inputEditText.setMaxLines(maxLines);
            }

            if (lines != 0) {
                this.inputEditText.setLines(lines);
            }
        }

        a.recycle();
    }

    public String getInput() {
        if (this.inputEditText != null) {
            return Build.VERSION.SDK_INT >= 19 ? ((Editable) Objects.requireNonNull(this.inputEditText.getText())).toString() : this.inputEditText.getText().toString();
        } else {
            return null;
        }
    }

    public void setInput(String input) {
        if (this.inputEditText != null) {
            this.inputEditText.setText(input);
        }

    }

    public void setWatcher(TextWatcher watcher) {
        if (this.inputEditText != null) {
            this.inputEditText.addTextChangedListener(watcher);
        }

    }

    public void USPhoneCode() {
        if (this.inputEditText != null) {
            this.inputEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
                private boolean backspacingFlag = false;
                private boolean editedFlag = false;
                private int cursorComplement;

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    this.cursorComplement = s.length() - CustomInputEditText.this.inputEditText.getSelectionStart();
                    this.backspacingFlag = count > after;
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    String string = s.toString();
                    String phone = string.replaceAll("[^\\d]", "");
                    if (!this.editedFlag) {
                        String ans;
                        if (phone.length() >= 6 && !this.backspacingFlag) {
                            this.editedFlag = true;
                            ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
                            CustomInputEditText.this.inputEditText.setText(ans);
                            CustomInputEditText.this.inputEditText.setSelection(CustomInputEditText.this.inputEditText.getText().length() - this.cursorComplement);
                        } else if (phone.length() >= 3 && !this.backspacingFlag) {
                            this.editedFlag = true;
                            ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
                            CustomInputEditText.this.inputEditText.setText(ans);
                            CustomInputEditText.this.inputEditText.setSelection(CustomInputEditText.this.inputEditText.getText().length() - this.cursorComplement);
                        }
                    } else {
                        this.editedFlag = false;
                    }

                }
            });
        }

    }

    public void setDrawable(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (this.inputEditText != null) {
            this.inputEditText.setCompoundDrawables(left, top, right, bottom);
        }

    }

    public void setInputType(int InputType) {
        if (this.inputEditText != null) {
            this.inputEditText.setInputType(InputType);
        }

    }

    public void removeError() {
        if (this.inputLayout != null && this.inputEditText != null) {
            this.bgHolder.setSelected(false);
            this.bgHolder.setActivated(false);
            this.bgHolder.setEnabled(false);
            this.errorMessage.setVisibility(GONE);
        }

    }

    public String getErrorMessage() {
        return this.inputEditText != null ? this.inputEditText.getError().toString() : null;
    }

    public void setFont(Typeface typeface) {
        if (this.inputLayout != null && this.inputEditText != null) {
            this.inputLayout.setTypeface(typeface);
            this.inputEditText.setTypeface(typeface);
            this.errorMessage.setTypeface(typeface);
        }

    }

    public void setErrorMessage(String message) {
        if (this.inputLayout != null && this.inputEditText != null) {
            this.bgHolder.setSelected(true);
            this.bgHolder.setActivated(true);
            this.bgHolder.setEnabled(true);
            if (message != null) {
                this.errorMessage.setText(message);
            }

            this.errorMessage.setVisibility(VISIBLE);
        }

    }

    public void setErrorMessage(String message, Drawable errorBackground) {
        if (this.inputLayout != null && this.inputEditText != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.bgHolder.setBackground(errorBackground);
            }

            if (message != null) {
                this.errorMessage.setText(message);
            }

            this.errorMessage.setVisibility(VISIBLE);
        }

    }

    public void setEms(int ems) {
        if (this.inputEditText != null && ems != 0) {
            this.inputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ems)});
        }

    }

    public void setMaxEms(int maxEms) {
        if (this.inputEditText != null && maxEms != 0) {
            this.inputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxEms)});
        }

    }

    public void setErrorMessage(String message, Drawable errorBackground, String errorColor) {
        if (this.inputLayout != null && this.inputEditText != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                this.bgHolder.setBackground(errorBackground);
            }

            if (message != null) {
                this.errorMessage.setText(message);
            }

            if (errorColor != null) {
                this.errorMessage.setTextColor(Color.parseColor(errorColor));
            }

            this.errorMessage.setVisibility(VISIBLE);
        }

    }

    public void setDigits(String digits, int[] InputType) {
        if (this.inputEditText != null) {
            this.inputEditText.setKeyListener(DigitsKeyListener.getInstance(digits));
            int[] var3 = InputType;
            int var4 = InputType.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                int type = var3[var5];
                this.inputEditText.setRawInputType(type);
            }
        }

    }

    public void nullTextWatcher() {
        if (this.inputEditText != null) {
            this.inputEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void afterTextChanged(Editable editable) {
                    String string = editable.toString();
                    if (string.length() > 0) {
                        CustomInputEditText.this.removeError();
                    }

                }
            });
        }

    }

    public void emailTextWatcher(final String errorMessage) {
        if (this.inputEditText != null) {
            this.inputEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void afterTextChanged(Editable editable) {
                    String string = editable.toString();
                    if (string.length() > 0) {
                        if (Patterns.EMAIL_ADDRESS.matcher(string).find()) {
                            CustomInputEditText.this.removeError();
                        } else {
                            CustomInputEditText.this.setErrorMessage(errorMessage);
                        }
                    }

                }
            });
        }

    }
}
