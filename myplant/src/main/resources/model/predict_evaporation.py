# coding=utf-8
import sys
from sklearn.externals import joblib
from sklearn.preprocessing import StandardScaler
from sklearn import svm
import numpy as np

def function(LAST_WATER_TIME, AT_MEAN, AM_MEAN, AT_VARIANCE, AM_VARIANCE, AT_SUM,
             AM_SUM, PLANT_KIND, SOIL_KIND, PLANT_VOLUME, POT_VOLUME, DBEFORE_SOIL_MOISTURE):
    clf = joblib.load("C:\\Users\\s3\\Desktop\\train_model.m")
    X = [float(LAST_WATER_TIME), float(AT_MEAN), float(AM_MEAN),
                       float(AT_VARIANCE), float(AM_VARIANCE),float(AT_SUM),
                       float(AM_SUM), float(PLANT_KIND), float(SOIL_KIND),
                       float(PLANT_VOLUME), float(POT_VOLUME), float(DBEFORE_SOIL_MOISTURE)]
    scaler = StandardScaler()  # 标准化转换
    scaler.fit(X)  # 训练标准化对象
    X = scaler.transform(X)  # 转换数据集

    print(clf.predict(X)[0])

function(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4],
         sys.argv[5], sys.argv[6], sys.argv[7], sys.argv[8],
         sys.argv[9], sys.argv[10], sys.argv[11], sys.argv[12])
#print(function(12, 22.23, 31.75, 0.03, 1.67, 511.3, 729.92, 2, 3, 3, 1, 66))
