package ir.garlic.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import java.util.Iterator;
import java.util.Set;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {
    private BidServiceAsync bidService;
    private Tree tree;
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        tree = new Tree();
        bidService = (BidServiceAsync)GWT.create(BidService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) bidService;
        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
        endpoint.setServiceEntryPoint(moduleRelativeUrl);
        bidService.getBidder(1, new AsyncCallback() {

                        public void onFailure(Throwable arg0) {

                        }

                        public void onSuccess(Object author) {

                                JSONValue value = JSONParser.parse((String)author);
                                tree.removeItems();
                                tree.setVisible(true);
                                TreeItem item = tree.addItem("Response");
                                addChildren(item, value);

                        }

        });

        RootPanel panel = RootPanel.get("auctionContainer");
                if(panel != null) {
                        panel.add(tree);
                }

    }
    private void addChildren(TreeItem treeItem, JSONValue jsonValue) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        JSONString jsonString;

        if ((jsonArray = jsonValue.isArray()) != null) {
            for (int i = 0; i < jsonArray.size(); ++i) {
                TreeItem child = treeItem.addItem(getChildText("[" + Integer.toString(i) + "]"));
                addChildren(child, jsonArray.get(i));
            }
        } else if ((jsonObject = jsonValue.isObject()) != null) {
            Set keys = jsonObject.keySet();
            for (Iterator iter = keys.iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                TreeItem child = treeItem.addItem(getChildText(key));
                addChildren(child, jsonObject.get(key));
            }
        } else if ((jsonString = jsonValue.isString()) != null) {
            treeItem.addItem(jsonString.stringValue());
        } else {
            treeItem.addItem(getChildText(jsonValue.toString()));
        }
    }

    private String getChildText(String text) {
        return "<span style='white-space:normal'>" + text + "</span>";
    }

}
