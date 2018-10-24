package com.example.xiaweizi.customviewtest.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.common.Util
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/24
 *     desc   :
 * </pre>
 */

public class Util {

    public static void saveBitmapToLocalJPEG(Context c, String path, String fileName, Bitmap mBitmap, boolean toast) {
        // 判断是否存在图片路径
        if (!isFileExist(path)) {
            createDirs(path);
        }
        File f = new File(path + fileName + ".jpg");
        if (f.exists()) {
            if (toast) {
                Toast.makeText(c, "图片已保存到" + path, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        BufferedOutputStream os = null;
        try {
            f.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(f));
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        } catch (IOException e) {
            if (toast) {
                Toast.makeText(c, "图片保存失败", Toast.LENGTH_SHORT).show();
            }
            e.printStackTrace();
        }

        if (os != null) {
            try {
                os.close();
                if (toast) {
                    Toast.makeText(c, "图片已保存到" + path, Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                if (toast) {
                    Toast.makeText(c, "图片保存失败", Toast.LENGTH_SHORT).show();
                }
                e.printStackTrace();
            }
        }
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static File createDirs(String dirName) {
        File dir = new File(dirName);
        dir.mkdirs();
        return dir;
    }

    public static Bitmap createWaterBitmap(Bitmap src, Bitmap maskBmp) {
        if (src == null) return null;
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int maskBmpWidth = maskBmp.getWidth();
        int maskBmpHeight = maskBmp.getHeight();

        Bitmap newBmp = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBmp);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(maskBmp, srcWidth - maskBmpWidth - 20, 20, null);
        return newBmp;
    }
}
