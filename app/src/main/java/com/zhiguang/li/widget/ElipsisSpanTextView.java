package com.zhiguang.li.widget;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 *  * Created by 智光 on 2019/11/13 15:03
 *  https://github.com/echoMu/ElipsisArrowTextView
 */
public class ElipsisSpanTextView extends AppCompatTextView {

    private static final String CLASS_NAME_VIEW = "android.view.View";
    private static final String CLASS_NAME_LISTENER_INFO = "android.view.View$ListenerInfo";
    private static final String ELLIPSIS_HINT = "...";
    private static final String GAP_TO_EXPAND_HINT = " ";
    private static final int MAX_LINES_ON_SHRINK = 3;

    private String mEllipsisHint;
    private String mGapToExpandHint = GAP_TO_EXPAND_HINT;
    private int mMaxLinesOnShrink = MAX_LINES_ON_SHRINK;

    private TextView.BufferType mBufferType = TextView.BufferType.NORMAL;
    private TextPaint mTextPaint;
    private Layout mLayout;
    private int mTextLineCount = -1;
    private int mLayoutWidth = 0;
    private int mFutureTextViewWidth = 0;

    //  the original text of this view
    private CharSequence mOrigText;


    private SpannableString endSpann;

    public ElipsisSpanTextView(Context context) {
        super(context);
        init(context);
    }

    public ElipsisSpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public ElipsisSpanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }


    private void init(Context context) {
//        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.more);
//        imgSpan1 = new CenteredImageSpan(getContext(), R.mipmap.more);

        if (TextUtils.isEmpty(mEllipsisHint)) {
            mEllipsisHint = ELLIPSIS_HINT;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
                setTextInternal(getNewTextByConfig(), mBufferType);
            }
        });
    }

    /**
     * used in ListView or RecyclerView to update ExpandableTextView
     *
     * @param text                original text
     * @param futureTextViewWidth the width of ExpandableTextView in px unit,
     *                            used to get max line number of original text by given the width
     */
    public void updateForRecyclerView(CharSequence text, int futureTextViewWidth) {
        mFutureTextViewWidth = futureTextViewWidth;
        setText(text);
    }

    public void updateForRecyclerView(CharSequence text, TextView.BufferType type, int futureTextViewWidth) {
        mFutureTextViewWidth = futureTextViewWidth;
        setText(text, type);
    }

    /**
     * @param text              需要展示的原文案
     * @param endSpann          结尾的文案
     * @param mMaxLinesOnShrink 最大展示行数
     */
    public void setMaxLinesOnShrink(CharSequence text, SpannableString endSpann, int mMaxLinesOnShrink) {
        this.mMaxLinesOnShrink = mMaxLinesOnShrink;
        this.endSpann = endSpann;
        setText(text);
    }



    /**
     * refresh and get a will-be-displayed text by current configuration
     *
     * @return get a will-be-displayed text
     */
    public CharSequence getNewTextByConfig() {
        if (TextUtils.isEmpty(mOrigText)) {
            return mOrigText;
        }

        mLayout = getLayout();
        if (mLayout != null) {
            mLayoutWidth = mLayout.getWidth();
        }

        if (mLayoutWidth <= 0) {
            if (getWidth() == 0) {
                if (mFutureTextViewWidth == 0) {
                    return mOrigText;
                } else {
                    mLayoutWidth = mFutureTextViewWidth - getPaddingLeft() - getPaddingRight();
                }
            } else {
                mLayoutWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            }
        }

        mTextPaint = getPaint();

        mTextLineCount = -1;
        mLayout = null;
        mLayout = new DynamicLayout(mOrigText, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        mTextLineCount = mLayout.getLineCount();

        if (mTextLineCount <= mMaxLinesOnShrink) {
            return mOrigText;
        }

        int indexEnd = getValidLayout().getLineEnd(mMaxLinesOnShrink - 1);
        int indexStart = getValidLayout().getLineStart(mMaxLinesOnShrink - 1);
        int indexEndTrimmed = indexEnd
                - getLengthOfString(mEllipsisHint)
                - getLengthOfString(mGapToExpandHint);

        if (indexEndTrimmed <= indexStart) {
            indexEndTrimmed = indexEnd;
        }

        int remainWidth = getValidLayout().getWidth() -
                (int) (mTextPaint.measureText(mOrigText.subSequence(indexStart, indexEndTrimmed).toString()) + 0.5) - (int) mTextPaint.measureText(endSpann.toString());
        float widthTailReplaced = mTextPaint.measureText(getContentOfString(mEllipsisHint)
                + getContentOfString(mGapToExpandHint));

        int indexEndTrimmedRevised = indexEndTrimmed;
        if (remainWidth > widthTailReplaced) {
            int extraOffset = 0;
            int extraWidth = 0;
            while (remainWidth > widthTailReplaced + extraWidth) {
                extraOffset++;
                if (indexEndTrimmed + extraOffset <= mOrigText.length()) {
                    extraWidth = (int) (mTextPaint.measureText(
                            mOrigText.subSequence(indexEndTrimmed, indexEndTrimmed + extraOffset).toString()) + 0.5);
                } else {
                    break;
                }
            }
            indexEndTrimmedRevised += extraOffset - 1;
        } else {
            int extraOffset = 0;
            int extraWidth = 0;
            while (remainWidth + extraWidth < widthTailReplaced) {
                extraOffset--;
                if (indexEndTrimmed + extraOffset > indexStart) {
                    extraWidth = (int) (mTextPaint.measureText(mOrigText.subSequence(indexEndTrimmed + extraOffset, indexEndTrimmed).toString()) + 0.5);
                } else {
                    break;
                }
            }
            indexEndTrimmedRevised += extraOffset;
        }

        String fixText = removeEndLineBreak(mOrigText.subSequence(0, indexEndTrimmedRevised));
        SpannableStringBuilder ssbShrink = new SpannableStringBuilder(fixText);

        ssbShrink.append(mEllipsisHint);

//        if (issetSpecialColor) {
//            int lenth = ssbShrink.length();
//            if (specialColorLenth <= lenth) {
//                lenth = specialColorLenth;
//            }
//            ssbShrink.setSpan(colorSpan, specialColorStart, lenth, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }

        ssbShrink.append(getContentOfString(mGapToExpandHint));
        ssbShrink.append(endSpann);

//        ssbShrink.setSpan(imgSpan1, ssbShrink.length() - 1, ssbShrink.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ssbShrink;
    }

    private String removeEndLineBreak(CharSequence text) {
        String str = text.toString();
        while (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }


        Layout mLayout = new DynamicLayout(str, mTextPaint, mLayoutWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (mLayout.getLineCount() > mMaxLinesOnShrink) {
            if (str.contains("\n")) {
                str = str.substring(0, str.lastIndexOf("\n"));
            }
        }

        return str;
    }

    private Layout getValidLayout() {
        return mLayout != null ? mLayout : getLayout();
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        mOrigText = text;
        mBufferType = type;
        setTextInternal(getNewTextByConfig(), type);
    }

    private void setTextInternal(CharSequence text, TextView.BufferType type) {

        super.setText(text, type);
    }

    private int getLengthOfString(String string) {
        if (string == null) {
            return 0;
        }
        return string.length();
    }

    private String getContentOfString(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }

    public View.OnClickListener getOnClickListener(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return getOnClickListenerV14(view);
        } else {
            return getOnClickListenerV(view);
        }
    }

    private View.OnClickListener getOnClickListenerV(View view) {
        View.OnClickListener retrievedListener = null;
        try {
            Field field = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mOnClickListener");
            field.setAccessible(true);
            retrievedListener = (View.OnClickListener) field.get(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedListener;
    }

    private View.OnClickListener getOnClickListenerV14(View view) {
        View.OnClickListener retrievedListener = null;
        try {
            Field listenerField = Class.forName(CLASS_NAME_VIEW).getDeclaredField("mListenerInfo");
            Object listenerInfo = null;

            if (listenerField != null) {
                listenerField.setAccessible(true);
                listenerInfo = listenerField.get(view);
            }

            Field clickListenerField = Class.forName(CLASS_NAME_LISTENER_INFO).getDeclaredField("mOnClickListener");

            if (clickListenerField != null && listenerInfo != null) {
                clickListenerField.setAccessible(true);
                retrievedListener = (View.OnClickListener) clickListenerField.get(listenerInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrievedListener;
    }

//    boolean issetSpecialColor = false;//是否有不同字体需要变颜色
//    int specialColorStart;//开始变色的位置
//    int specialColorLenth;//需要变色的长度
//    ForegroundColorSpan colorSpan;
//
//    public void setSpecialColor(int start, int lenth, ForegroundColorSpan colorSpan) {
//        specialColorStart = start;
//        specialColorLenth = lenth;
//        this.colorSpan = colorSpan;
//        issetSpecialColor = true;
//    }
//
//    class CenteredImageSpan extends ImageSpan {
//
//        public CenteredImageSpan(Context context, final int drawableRes) {
//            super(context, drawableRes);
//        }
//
//        @Override
//        public void draw(@NonNull Canvas canvas, CharSequence text,
//                         int start, int end, float x,
//                         int top, int y, int bottom, @NonNull Paint paint) {
//            // image to draw
//            Drawable b = getDrawable();
//            // font metrics of text to be replaced
//            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
//            int transY = (y + fm.descent + y + fm.ascent) / 2
//                    - b.getBounds().bottom / 2;
//
//            canvas.save();
//            canvas.translate(x, transY);
//            b.draw(canvas);
//            canvas.restore();
//        }
//    }
}
