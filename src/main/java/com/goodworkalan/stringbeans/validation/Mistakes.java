package com.goodworkalan.stringbeans.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.goodworkalan.verbiage.Message;

/**
 * Wrapper around a map of mistakes, keyed by object identity, that lists
 * mistakes as a map of fields to a list of mistake messages.
 * <p>
 * Seems that for errors that apply to the entire object as a whole, you could
 * safely use the key this, which would collide only with a method named
 * <code>getThis()</code>, which would be a silly, silly method.
 * 
 * @author Alan Gutierrez
 */
public class Mistakes {
    /** The identity map of mistakes. */
    public final Map<Object, Map<String, List<Message>>> mistakes = new IdentityHashMap<Object, Map<String,List<Message>>>();

    /**
     * Record a mistake the given object with the given context field. The given
     * message key is used to find a message format in a message bundle. The
     * message format is used to create an error message with the given message
     * arguments.
     * 
     * @param object
     *            The object.
     * @param context
     *            The context field.
     * @param messageKey
     *            The message key.
     * @param arguments
     *            The message format arguments.
     */
    public void mistake(Object object, String context, String messageKey, Object...arguments) {
        Map<String, List<Message>> fields = mistakes.get(object);
        if (fields == null) {
            fields = new HashMap<String, List<Message>>();
            mistakes.put(object, fields);
        }
        List<Message> messages = fields.get(messageKey);
        if (messages == null) {
            messages = new ArrayList<Message>();
            fields.put(messageKey, messages);
        }
        messages.add(new Message(object.getClass().getName(), "errors", messageKey, Message.position(new HashMap<Object, Object>(), arguments)));
    }
}
