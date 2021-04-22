package com.app.remote_controller_app;

public class ConnectedThread extends Thread{


    // Call this from the main activity to send data to the remote device.
    public void write(byte[] bytes) {
//        try {
//            mmOutStream.write(bytes);
//
//            // Share the sent message with the UI activity.
//            Message writtenMsg = handler.obtainMessage(
//                    MessageConstants.MESSAGE_WRITE, -1, -1, bytes);
//            writtenMsg.sendToTarget();
//        } catch (IOException e) {
//            Log.e(TAG, "Error occurred when sending data", e);
//
//            // Send a failure message back to the activity.
//            Message writeErrorMsg =
//                    handler.obtainMessage(MessageConstants.MESSAGE_TOAST);
//            Bundle bundle = new Bundle();
//            bundle.putString("toast",
//                    "Couldn't send data to the other device");
//            writeErrorMsg.setData(bundle);
//            handler.sendMessage(writeErrorMsg);
//        }
    }
}
