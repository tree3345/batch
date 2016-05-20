/*
 * Copyright 2009-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lz.generation.step;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple minded partitioner for a range of values of a column in a database
 * table. Works best if the values are uniformly distributed (e.g.
 * auto-generated primary key values).
 *
 * @author Dave Syer
 */
public class ColumnRangePartitioner implements Partitioner {

    private JdbcOperations jdbcTemplate;

    private String table;

    private String column;


    private String keyName = "code";

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * The name of the SQL table the data are in.
     *
     * @param table the name of the table
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * The name of the column to partition.
     *
     * @param column the column name.
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * The data source for connecting to the database.
     *
     * @param dataSource a {@link DataSource}
     */
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Partition a database table assuming that the data in the column specified
     * are uniformly distributed. The execution context values will have keys
     * <code>minValue</code> and <code>maxValue</code> specifying the range of
     * values to consider in each partition.
     *
     * @see Partitioner#partition(int)
     */
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
//        List<Map<String, Object>> codes = jdbcTemplate.queryForList("SELECT " + column + " FROM " + table + " ORDER BY " + column+ " ");
        String codes ="LZ_GPA_ALLOT_ALLOT_PCT,LZ_GPA_ALLOT_BASE_SHR,LZ_GPA_BALGEN_BGCYC_MERG,LZ_GPA_BALGEN_BGCYC_PRNT,LZ_GPA_BALGEN_BGNTR_MERG,LZ_GPA_BALGEN_BGNTR_PRNT,LZ_GPA_BALGEN_BGTFAS_MERG,LZ_GPA_BALGEN_BGTFAS_PRNT,LZ_GPA_BALGEN_TTM_BGCYC_MERG,LZ_GPA_BALGEN_TTM_BGCYC_PRNT,LZ_GPA_BALGEN_TTM_BGNTR_MERG,LZ_GPA_BALGEN_TTM_BGNTR_PRNT,LZ_GPA_BALGEN_TTM_BGTFAS_MERG,LZ_GPA_BALGEN_TTM_BGTFAS_PRNT,LZ_GPA_BALGEN_TTM_YOY_BGCYC_MERG,LZ_GPA_BALGEN_TTM_YOY_BGCYC_PRNT,LZ_GPA_BALGEN_TTM_YOY_BGNTR_MERG,LZ_GPA_BALGEN_TTM_YOY_BGNTR_PRNT,LZ_GPA_BALGEN_TTM_YOY_BGTFAS_MERG,LZ_GPA_BALGEN_TTM_YOY_BGTFAS_PRNT,LZ_GPA_BALGEN_YOY_BGCYC_MERG,LZ_GPA_BALGEN_YOY_BGCYC_PRNT,LZ_GPA_BALGEN_YOY_BGNTR_MERG,LZ_GPA_BALGEN_YOY_BGNTR_PRNT,LZ_GPA_BALGEN_YOY_BGTFAS_MERG,LZ_GPA_BASEINFO_LIST_SECTOR,LZ_GPA_BASEINFO_MKT_TYPE,LZ_GPA_BNKBAL_BBCCBD_MERG,LZ_GPA_BNKBAL_BBCCBD_PRNT,LZ_GPA_BNKBAL_BBLDF_MERG,LZ_GPA_BNKBAL_BBLDF_PRNT,LZ_GPA_BNKBAL_BBMTL_MERG,LZ_GPA_BNKBAL_BBMTL_PRNT,LZ_GPA_BNKBAL_TTM_BBCCBD_MERG,LZ_GPA_BNKBAL_TTM_BBCCBD_PRNT,LZ_GPA_BNKBAL_TTM_BBLDF_MERG,LZ_GPA_BNKBAL_TTM_BBLDF_PRNT,LZ_GPA_BNKBAL_TTM_BBMTL_MERG,LZ_GPA_BNKBAL_TTM_BBMTL_PRNT,LZ_GPA_BNKBAL_TTM_YOY_BBCCBD_MERG,LZ_GPA_BNKBAL_TTM_YOY_BBCCBD_PRNT,LZ_GPA_BNKBAL_TTM_YOY_BBLDF_MERG,LZ_GPA_BNKBAL_TTM_YOY_BBLDF_PRNT,LZ_GPA_BNKBAL_TTM_YOY_BBMTL_MERG,LZ_GPA_BNKBAL_TTM_YOY_BBMTL_PRNT,LZ_GPA_BNKBAL_YOY_BBCCBD_MERG,LZ_GPA_BNKBAL_YOY_BBCCBD_PRNT,LZ_GPA_BNKBAL_YOY_BBLDF_MERG,LZ_GPA_BNKBAL_YOY_BBLDF_PRNT,LZ_GPA_BNKBAL_YOY_BBMTL_MERG,LZ_GPA_BNKBAL_YOY_BBMTL_PRNT,LZ_GPA_BNKCASH_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_BCCBLNNIRS_PRNT,LZ_GPA_BNKCASH_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_BCOFCUNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_QTR_BCCBLNNIRS_PRNT,LZ_GPA_BNKCASH_QTR_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_BCOFCUNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_TTM_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_QTR_TTM_BCCBLNNIRS_PRNT,LZ_GPA_BNKCASH_QTR_TTM_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_TTM_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_TTM_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_TTM_BCOFCUNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCCBLNNIRS_PRNT,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_TTM_YOY_BCOFCUNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_YOY_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_QTR_YOY_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_QTR_YOY_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_QTR_YOY_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_YOY_BCCBLNNIRS_MERG,LZ_GPA_BNKCASH_YOY_BCCBLNNIRS_PRNT,LZ_GPA_BNKCASH_YOY_BCCTBDNIRSAT_MERG,LZ_GPA_BNKCASH_YOY_BCCTBDNIRSAT_PRNT,LZ_GPA_BNKCASH_YOY_BCOFCUNIRSAT_MERG,LZ_GPA_BNKCASH_YOY_BCOFCUNIRSAT_PRNT,LZ_GPA_BNKFIN_BFIBBW,LZ_GPA_BNKFIN_BFITTD,LZ_GPA_BNKFIN_BFITTLN,LZ_GPA_BNKINCM_BIBIM_MERG,LZ_GPA_BNKINCM_BIBIM_PRNT,LZ_GPA_BNKINCM_BIIRI_MERG,LZ_GPA_BNKINCM_BIIRI_PRNT,LZ_GPA_BNKINCM_BIIRNI_MERG,LZ_GPA_BNKINCM_BIIRNI_PRNT,LZ_GPA_BNKINCM_QTR_BIBIM_PRNT,LZ_GPA_BNKINCM_QTR_BIIRI_MERG,LZ_GPA_BNKINCM_QTR_BIIRI_PRNT,LZ_GPA_BNKINCM_QTR_BIIRNI_MERG,LZ_GPA_BNKINCM_QTR_BIIRNI_PRNT,LZ_GPA_BNKINCM_QTR_TTM_BIBIM_MERG,LZ_GPA_BNKINCM_QTR_TTM_BIBIM_PRNT,LZ_GPA_BNKINCM_QTR_TTM_BIIRI_MERG,LZ_GPA_BNKINCM_QTR_TTM_BIIRI_PRNT,LZ_GPA_BNKINCM_QTR_TTM_BIIRNI_MERG,LZ_GPA_BNKINCM_QTR_TTM_BIIRNI_PRNT,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIBIM_MERG,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIBIM_PRNT,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIIRI_MERG,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIIRI_PRNT,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIIRNI_MERG,LZ_GPA_BNKINCM_QTR_TTM_YOY_BIIRNI_PRNT,LZ_GPA_BNKINCM_QTR_YOY_BIBIM_MERG,LZ_GPA_BNKINCM_QTR_YOY_BIBIM_PRNT,LZ_GPA_BNKINCM_QTR_YOY_BIIRI_MERG,LZ_GPA_BNKINCM_QTR_YOY_BIIRI_PRNT,LZ_GPA_BNKINCM_QTR_YOY_BIIRNI_MERG,LZ_GPA_BNKINCM_QTR_YOY_BIIRNI_PRNT,LZ_GPA_BNKINCM_YOY_BIBIM_MERG,LZ_GPA_BNKINCM_YOY_BIBIM_PRNT,LZ_GPA_BNKINCM_YOY_BIIRI_MERG,LZ_GPA_BNKINCM_YOY_BIIRI_PRNT,LZ_GPA_BNKINCM_YOY_BIIRNI_MERG,LZ_GPA_BNKINCM_YOY_BIIRNI_PRNT,LZ_GPA_CASHGEN_CGROBC_MERG,LZ_GPA_CASHGEN_CGROBC_PRNT,LZ_GPA_CASHGEN_CGRTTX_MERG,LZ_GPA_CASHGEN_CGRTTX_PRNT,LZ_GPA_CASHGEN_CGSPRRC_MERG,LZ_GPA_CASHGEN_CGSPRRC_PRNT,LZ_GPA_CASHGEN_QTR_CGROBC_MERG,LZ_GPA_CASHGEN_QTR_CGROBC_PRNT,LZ_GPA_CASHGEN_QTR_CGRTTX_MERG,LZ_GPA_CASHGEN_QTR_CGRTTX_PRNT,LZ_GPA_CASHGEN_QTR_CGSPRRC_MERG,LZ_GPA_CASHGEN_QTR_CGSPRRC_PRNT,LZ_GPA_CASHGEN_QTR_TTM_CGROBC_MERG,LZ_GPA_CASHGEN_QTR_TTM_CGROBC_PRNT,LZ_GPA_CASHGEN_QTR_TTM_CGRTTX_MERG,LZ_GPA_CASHGEN_QTR_TTM_CGRTTX_PRNT,LZ_GPA_CASHGEN_QTR_TTM_CGSPRRC_MERG,LZ_GPA_CASHGEN_QTR_TTM_CGSPRRC_PRNT,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGROBC_MERG,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGROBC_PRNT,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGRTTX_MERG,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGRTTX_PRNT,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGSPRRC_MERG,LZ_GPA_CASHGEN_QTR_TTM_YOY_CGSPRRC_PRNT,LZ_GPA_CASHGEN_QTR_YOY_CGROBC_MERG,LZ_GPA_CASHGEN_QTR_YOY_CGROBC_PRNT,LZ_GPA_CASHGEN_QTR_YOY_CGRTTX_MERG,LZ_GPA_CASHGEN_QTR_YOY_CGRTTX_PRNT,LZ_GPA_CASHGEN_QTR_YOY_CGSPRRC_MERG,LZ_GPA_CASHGEN_QTR_YOY_CGSPRRC_PRNT,LZ_GPA_CASHGEN_YOY_CGROBC_MERG,LZ_GPA_CASHGEN_YOY_CGROBC_PRNT,LZ_GPA_CASHGEN_YOY_CGRTTX_MERG,LZ_GPA_CASHGEN_YOY_CGRTTX_PRNT,LZ_GPA_CASHGEN_YOY_CGSPRRC_MERG,LZ_GPA_CASHGEN_YOY_CGSPRRC_PRNT,LZ_GPA_DIVINFO_BASE_VOL,LZ_GPA_DIVINFO_CASH_AT,LZ_GPA_DIVINFO_CASH_BT,LZ_GPA_EXCHGNCAL,LZ_GPA_FIN_FIPBEPS,LZ_GPA_FIN_FIPDUEPS,LZ_GPA_FIN_FIPEUDUEPSED,LZ_GPA_FREESHR_FREE_ASHR,LZ_GPA_FREESHR_MNG_SHR,LZ_GPA_FREESHR_SH_SHR,LZ_GPA_HYVAL_SWN_PB,LZ_GPA_HYVAL_SWN_PC,LZ_GPA_HYVAL_SWN_PE,LZ_GPA_HYVAL_XCF_PB,LZ_GPA_HYVAL_XCF_PC,LZ_GPA_HYVAL_XCF_PE,LZ_GPA_HYVAL_ZJH_PB,LZ_GPA_HYVAL_ZJH_PC,LZ_GPA_HYVAL_ZJH_PE,LZ_GPA_INCMGEN_IGBCTT_MERG,LZ_GPA_INCMGEN_IGBCTT_PRNT,LZ_GPA_INCMGEN_IGBITT_MERG,LZ_GPA_INCMGEN_IGBITT_PRNT,LZ_GPA_INCMGEN_IGBI_MERG,LZ_GPA_INCMGEN_IGBI_PRNT,LZ_GPA_INCMGEN_QTR_IGBCTT_MERG,LZ_GPA_INCMGEN_QTR_IGBCTT_PRNT,LZ_GPA_INCMGEN_QTR_IGBITT_PRNT,LZ_GPA_INCMGEN_QTR_IGBI_MERG,LZ_GPA_INCMGEN_QTR_IGBI_PRNT,LZ_GPA_INCMGEN_QTR_TTM_IGBCTT_MERG,LZ_GPA_INCMGEN_QTR_TTM_IGBCTT_PRNT,LZ_GPA_INCMGEN_QTR_TTM_IGBITT_MERG,LZ_GPA_INCMGEN_QTR_TTM_IGBITT_PRNT,LZ_GPA_INCMGEN_QTR_TTM_IGBI_MERG,LZ_GPA_INCMGEN_QTR_TTM_IGBI_PRNT,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBCTT_MERG,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBCTT_PRNT,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBITT_MERG,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBITT_PRNT,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBI_MERG,LZ_GPA_INCMGEN_QTR_TTM_YOY_IGBI_PRNT,LZ_GPA_INCMGEN_QTR_YOY_IGBCTT_MERG,LZ_GPA_INCMGEN_QTR_YOY_IGBCTT_PRNT,LZ_GPA_INCMGEN_QTR_YOY_IGBITT_MERG,LZ_GPA_INCMGEN_QTR_YOY_IGBITT_PRNT,LZ_GPA_INCMGEN_QTR_YOY_IGBI_MERG,LZ_GPA_INCMGEN_QTR_YOY_IGBI_PRNT,LZ_GPA_INCMGEN_YOY_IGBCTT_MERG,LZ_GPA_INCMGEN_YOY_IGBCTT_PRNT,LZ_GPA_INCMGEN_YOY_IGBITT_MERG,LZ_GPA_INCMGEN_YOY_IGBITT_PRNT,LZ_GPA_INCMGEN_YOY_IGBI_MERG,LZ_GPA_INCMGEN_YOY_IGBI_PRNT,LZ_GPA_INDUCSRC_CSRC_NAME1,LZ_GPA_INDUCSRC_CSRC_NAME2,LZ_GPA_INDUSW_SW_NAME1,LZ_GPA_INDUSW_SW_NAME2,LZ_GPA_INDUSW_SW_NAME3,LZ_GPA_INDUXCF_CF_NAME1,LZ_GPA_INDU_SWN,LZ_GPA_INDU_XCF,LZ_GPA_INDU_ZJH,LZ_GPA_INDXQUOTE_CHNG,LZ_GPA_INDXQUOTE_CHNG_PCT,LZ_GPA_INDXQUOTE_LCLOSE,LZ_GPA_INDXQUOTE_TCLOSE,LZ_GPA_INDXQUOTE_THIGH,LZ_GPA_INDXQUOTE_TOPEN,LZ_GPA_INDXVAL_PB,LZ_GPA_INDXVAL_PC,LZ_GPA_INDXVAL_PE,LZ_GPA_INDXVAL_PE1,LZ_GPA_INDXVAL_PE2,LZ_GPA_INDXVAL_PE4,LZ_GPA_INDXWGT_WPCT_000010,LZ_GPA_INDXWGT_WPCT_000015,LZ_GPA_INDXWGT_WPCT_000042,LZ_GPA_INDXWGT_WPCT_000300,LZ_GPA_INDXWGT_WPCT_000905,LZ_GPA_INDXWGT_WPCT_000926,LZ_GPA_INDXWGT_WPCT_000927,LZ_GPA_ISS_EPS,LZ_GPA_ISS_NAPS,LZ_GPA_ISS_PRC,LZ_GPA_MKTEXPR_MKTCAP,LZ_GPA_MKTEXPR_TCAP,LZ_GPA_MKTEXPR_TRADE_STATUS,LZ_GPA_MKTEXPR_TURNOVER_DAY,LZ_GPA_NONBNKBAL_NBBCTD_MERG,LZ_GPA_NONBNKBAL_NBBCTD_PRNT,LZ_GPA_NONBNKBAL_NBBCYC_MERG,LZ_GPA_NONBNKBAL_NBBCYC_PRNT,LZ_GPA_NONBNKBAL_NBBPLGLN_MERG,LZ_GPA_NONBNKBAL_TTM_NBBCTD_MERG,LZ_GPA_NONBNKBAL_TTM_NBBCTD_PRNT,LZ_GPA_NONBNKBAL_TTM_NBBCYC_MERG,LZ_GPA_NONBNKBAL_TTM_NBBCYC_PRNT,LZ_GPA_NONBNKBAL_TTM_NBBPLGLN_MERG,LZ_GPA_NONBNKBAL_TTM_YOY_NBBCTD_MERG,LZ_GPA_NONBNKBAL_TTM_YOY_NBBCTD_PRNT,LZ_GPA_NONBNKBAL_TTM_YOY_NBBCYC_MERG,LZ_GPA_NONBNKBAL_TTM_YOY_NBBCYC_PRNT,LZ_GPA_NONBNKBAL_TTM_YOY_NBBPLGLN_MERG,LZ_GPA_NONBNKBAL_YOY_NBBCTD_MERG,LZ_GPA_NONBNKBAL_YOY_NBBCTD_PRNT,LZ_GPA_NONBNKBAL_YOY_NBBCYC_MERG,LZ_GPA_NONBNKBAL_YOY_NBBCYC_PRNT,LZ_GPA_NONBNKBAL_YOY_NBBPLGLN_MERG,LZ_GPA_NONBNKCASH_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKCASH_QTR_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_QTR_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_QTR_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_QTR_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_QTR_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_QTR_TTM_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_QTR_TTM_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_YOY_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_YOY_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_QTR_TTM_YOY_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_QTR_TTM_YOY_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_QTR_TTM_YOY_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKCASH_QTR_YOY_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_QTR_YOY_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_QTR_YOY_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_QTR_YOY_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_QTR_YOY_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKCASH_YOY_NBCDSTDFANIRS_MERG,LZ_GPA_NONBNKCASH_YOY_NBCDSTDFANIRS_PRNT,LZ_GPA_NONBNKCASH_YOY_NBCRIRFCMSC_MERG,LZ_GPA_NONBNKCASH_YOY_NBCRIRFCMSC_PRNT,LZ_GPA_NONBNKCASH_YOY_NBCROISRCRPUC_MERG,LZ_GPA_NONBNKINCM_NBIBI_MERG,LZ_GPA_NONBNKINCM_NBIBI_PRNT,LZ_GPA_NONBNKINCM_NBIIRI_MERG,LZ_GPA_NONBNKINCM_NBIIRNI_MERG,LZ_GPA_NONBNKINCM_NBIIRNI_PRNT,LZ_GPA_NONBNKINCM_QTR_NBIBI_PRNT,LZ_GPA_NONBNKINCM_QTR_NBIIRI_MERG,LZ_GPA_NONBNKINCM_QTR_NBIIRNI_MERG,LZ_GPA_NONBNKINCM_QTR_NBIIRNI_PRNT,LZ_GPA_NONBNKINCM_QTR_TTM_NBIBI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_NBIBI_PRNT,LZ_GPA_NONBNKINCM_QTR_TTM_NBIIRI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_NBIIRI_PRNT,LZ_GPA_NONBNKINCM_QTR_TTM_NBIIRNI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_NBIIRNI_PRNT,LZ_GPA_NONBNKINCM_QTR_TTM_YOY_NBIBI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_YOY_NBIBI_PRNT,LZ_GPA_NONBNKINCM_QTR_TTM_YOY_NBIIRI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_YOY_NBIIRNI_MERG,LZ_GPA_NONBNKINCM_QTR_TTM_YOY_NBIIRNI_PRNT,LZ_GPA_NONBNKINCM_QTR_YOY_NBIBI_MERG,LZ_GPA_NONBNKINCM_QTR_YOY_NBIBI_PRNT,LZ_GPA_NONBNKINCM_QTR_YOY_NBIIRI_MERG,LZ_GPA_NONBNKINCM_QTR_YOY_NBIIRNI_MERG,LZ_GPA_NONBNKINCM_QTR_YOY_NBIIRNI_PRNT,LZ_GPA_NONBNKINCM_YOY_NBIBI_MERG,LZ_GPA_NONBNKINCM_YOY_NBIBI_PRNT,LZ_GPA_NONBNKINCM_YOY_NBIIRI_MERG,LZ_GPA_QUOTE_CHNG,LZ_GPA_QUOTE_CHNG_PCT,LZ_GPA_QUOTE_CUM_FACTOR,LZ_GPA_QUOTE_LCLOSE,LZ_GPA_QUOTE_TCLOSE,LZ_GPA_QUOTE_THIGH,LZ_GPA_QUOTE_TOPEN,LZ_GPA_STPCLDR_STOPFLAG,LZ_GPA_STRU_FL_ASHR,LZ_GPA_STRU_TOTAL,LZ_GPA_STRU_TOT_LTDFL,LZ_GPA_TMP_INDX,LZ_GPA_TMP_STK,LZ_GPA_VAL_PB,LZ_GPA_VAL_PC,LZ_GPA_VAL_PE,LZ_GPA_VAL_PE1,LZ_GPA_VAL_PE2,LZ_GPA_YLDMNTH_CHNG,LZ_GPA_YLDMNTH_CHNG_PCT,LZ_GPA_YLDMNTH_LCLOSE,LZ_GPA_YLDMNTH_TCLOSE,LZ_GPA_YLDMNTH_TCLOSE_HIGH,LZ_GPA_YLDMNTH_TCLOSE_LOW,LZ_GPA_YLDMNTH_THIGH,LZ_GPA_YLDMNTH_TOPEN,LZ_GPA_YLDWEEK_CHNG,LZ_GPA_YLDWEEK_CHNG_PCT,LZ_GPA_YLDWEEK_LCLOSE,LZ_GPA_YLDWEEK_TCLOSE,LZ_GPA_YLDWEEK_TCLOSE_HIGH,LZ_GPA_YLDWEEK_TCLOSE_LOW,LZ_GPA_YLDWEEK_THIGH,LZ_GPA_YLDWEEK_TOPEN,RiskFactorsBeta,RiskFactorsBTOP,RiskFactorsEarningsYield,RiskFactorsGrowth,RiskFactorsLeverage,RiskFactorsLiquidity,RiskFactorsMomentum,RiskFactorsNonSize,RiskFactorsResidualVolatility,RiskFactorsSize,RiskFactors_AnlyContrib,RiskFactors_AnlyResid";
        System.out.println("codes-----------" + codes);
        String[] codeArrs = codes.split(",");
        Map<String, ExecutionContext> partitionMap = new HashMap<String, ExecutionContext>();
//        List<String> codes = getCodes();
       /* for (String code : codes.split(",")) {
            ExecutionContext context = new ExecutionContext();
            context.put("code", code);
            partitionMap.put("partition"+code, context);
        }*/
        for(int i =0;i<3000;i++){
            ExecutionContext context = new ExecutionContext();
            context.put("code", "code"+i);
            partitionMap.put("partition"+i, context);
        }
        System.out.println(partitionMap);
        return partitionMap;
    }
}
