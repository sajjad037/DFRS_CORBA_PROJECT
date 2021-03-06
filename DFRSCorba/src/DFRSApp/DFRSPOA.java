package DFRSApp;

/**
 * Interface definition: DFRS.
 * 
 * @author OpenORB Compiler
 */
public abstract class DFRSPOA extends org.omg.PortableServer.Servant
        implements DFRSOperations, org.omg.CORBA.portable.InvokeHandler
{
    public DFRS _this()
    {
        return DFRSHelper.narrow(_this_object());
    }

    public DFRS _this(org.omg.CORBA.ORB orb)
    {
        return DFRSHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:DFRSApp/DFRS:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("addFlight")) {
                return _invoke_addFlight(_is, handler);
        } else if (opName.equals("bookFlight")) {
                return _invoke_bookFlight(_is, handler);
        } else if (opName.equals("editFlightRecord")) {
                return _invoke_editFlightRecord(_is, handler);
        } else if (opName.equals("getBookedFlightCount")) {
                return _invoke_getBookedFlightCount(_is, handler);
        } else if (opName.equals("resetCount")) {
                return _invoke_resetCount(_is, handler);
        } else if (opName.equals("transferReservation")) {
                return _invoke_transferReservation(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_bookFlight(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();
        String arg5_in = _is.read_string();
        String arg6_in = _is.read_string();

        String _arg_result = bookFlight(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in, arg6_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_getBookedFlightCount(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();

        String _arg_result = getBookedFlightCount(arg0_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_editFlightRecord(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        editFlightRecord(arg0_in, arg1_in, arg2_in);

        _output = handler.createReply();

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_addFlight(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        int arg0_in = _is.read_long();
        int arg1_in = _is.read_long();
        int arg2_in = _is.read_long();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();

        String _arg_result = addFlight(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_transferReservation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        transferReservation(arg0_in, arg1_in, arg2_in);

        _output = handler.createReply();

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_resetCount(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        resetCount();

        _output = handler.createReply();

        return _output;
    }

}
