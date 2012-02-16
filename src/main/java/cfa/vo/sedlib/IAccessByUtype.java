/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfa.vo.sedlib;

import cfa.vo.sedlib.common.SedInconsistentException;

/**
 *
 * @author mcd
 */
public interface IAccessByUtype {

    Object getValueByUtype(int utypeNum, boolean create) throws SedInconsistentException;

    void setValueByUtype(int utypeNum, Object value) throws SedInconsistentException;

}
