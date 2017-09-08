/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package com.pingxun.library.guomeilibrary.bridge;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ConfigXmlParser {


    private boolean insideParam;
    private String handlerName = "", handlerClass = "";
    private ArrayList<HandlerHolder> handlerHolders=new ArrayList<>();

    public void parse(Context context, String xml) {
        // First checking the class namespace for config.xml
        int id = context.getResources().getIdentifier(xml, "xml", context.getClass().getPackage().getName());
        if (id == 0) {
            // If we couldn't find config.xml there, we'll look in the namespace from AndroidManifest.xml
            id = context.getResources().getIdentifier(xml, "xml", context.getPackageName());
            if (id == 0) {
                throw new RuntimeException("Cannot find" +xml+".xml file");
            }
        }
        parse(context.getResources().getXml(id));
    }

    public ArrayList<HandlerHolder> getHandlerHolder(){
        return handlerHolders;
    }
    private void parse(XmlPullParser xml) {
        int eventType = -1;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                handleStartTag(xml);
            } else if (eventType == XmlPullParser.END_TAG) {
                handleEndTag(xml);
            }
            try {
                eventType = xml.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleStartTag(XmlPullParser xml) {
        String strNode = xml.getName();
        if (strNode.equals("param")) {
            insideParam = true;
        } else if (insideParam ) {
            if (strNode.equals("name")) {
                handlerName = xml.getAttributeValue(null, "value");
            } else if (strNode.equals("handler")) {
                handlerClass = xml.getAttributeValue(null, "value");
            }
        }
    }

    private void handleEndTag(XmlPullParser xml) {
        String strNode = xml.getName();
        if (strNode.equals("param")) {
            handlerHolders.add(new HandlerHolder(handlerName, handlerClass));
            handlerName = "";
            handlerClass = "";
            insideParam = false;
        }
    }

}
