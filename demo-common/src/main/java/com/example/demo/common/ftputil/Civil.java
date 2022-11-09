package com.example.demo.common.ftputil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yu.zhang
 */
public class Civil {


	private final Logger logger = LoggerFactory.getLogger(Civil.class);

	private List<String> nelFileNames = new ArrayList<>();
	private List<String> eqhFileNames = new ArrayList<>();
	private List<String> crdFileNames = new ArrayList<>();
	private List<String> prtFileNames = new ArrayList<>();
	private List<String> tplFileNames = new ArrayList<>();
	private List<String> ethFileNames = new ArrayList<>();
	private List<String> tdmFileNames = new ArrayList<>();
	private List<String> tnlFileNames = new ArrayList<>();
	private List<String> pswFileNames = new ArrayList<>();
	private List<String> l3iFileNames = new ArrayList<>();

	private void analyseData(String[] fileNames, String type) {
		logger.info("OmcMigrationDataToDB start...");

		for (String fileName : fileNames) {
			if (fileName.indexOf("Me") > 0) {
				nelFileNames.add(fileName);
			} else if (fileName.indexOf("EhT") > 0) {
				eqhFileNames.add(fileName);
			} else if (fileName.indexOf("EqT") > 0) {
				crdFileNames.add(fileName);
			}else if (fileName.indexOf("Port") > 0 ) {
				prtFileNames.add(fileName);
			}
		}

		String ME = "ME";
		if (type == null || ME.equalsIgnoreCase(type)) {
			try {
				if (nelFileNames.size() > 0) {
					logger.info(" Process Me data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(nelFileNames);
					//处理数据拼接m_ObjectKey
					logger.info("NEL file data count:" + omcRmuidRelList.size());
					for (OmcRmuidRel omcRmuidRel : omcRmuidRelList) {
						omcRmuidRel.m_ObjectKey=omcRmuidRel.m_emsId+"--"+omcRmuidRel.m_ManagedElement;
						 logger.info(omcRmuidRel.toString());
						 System.out.println();
						 String aa= "";
					}
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("me", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Me data to DB start Failed !");
						return;
					}
					logger.info(" Process Me data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Me data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "EhT".equalsIgnoreCase(type)) {
			try {
				if (eqhFileNames.size() > 0) {
					logger.info(" Process EhT data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(eqhFileNames);
					List<OmcRmuidRel> rackList= new ArrayList<OmcRmuidRel>();
					List<OmcRmuidRel> shelfList= new ArrayList<OmcRmuidRel>();
					List<OmcRmuidRel> slotList= new ArrayList<OmcRmuidRel>();
					for (OmcRmuidRel omcRmuidRel : omcRmuidRelList) {
						String m_ObjectTmfName = omcRmuidRel.m_ObjectTmfName;
						String objectid = getInfo(m_ObjectTmfName, "EquipmentHolder");
						if (m_ObjectTmfName.indexOf("slot")>0) {
						//	omcRmuidRel.m_ObjectKey=Util.getSlotObjectKey(omcRmuidRel.m_emsId,omcRmuidRel.m_ManagedElement,objectid);
							slotList.add(omcRmuidRel);
						}else if (m_ObjectTmfName.indexOf("shelf")>0){
					//		omcRmuidRel.m_ObjectKey=Util.getShelfObjectKey(omcRmuidRel.m_emsId,omcRmuidRel.m_ManagedElement,objectid);
							shelfList.add(omcRmuidRel);
						}else if (m_ObjectTmfName.indexOf("rack")>0){
						//	int [] rack = Util.getRack(objectid);
						//	omcRmuidRel.m_ObjectKey = omcRmuidRel.m_emsId+"--"+omcRmuidRel.m_ManagedElement+"--"+rack[0];
							rackList.add(omcRmuidRel);
						}
					}












					logger.info("EQH file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("equip", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Equip data to DB start Failed !");
						return;
					}
					logger.info(" Process Equip data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Equip data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "EqT".equalsIgnoreCase(type)) {
			try {
				if (crdFileNames.size() > 0) {
					logger.info(" Process Card data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(crdFileNames);
					for (OmcRmuidRel omcRmuidRel : omcRmuidRelList) {
						String m_ObjectTmfName = omcRmuidRel.m_ObjectTmfName;
						String slotId = getInfo(m_ObjectTmfName, "EquipmentHolder");
						String cardNumber = getInfo(m_ObjectTmfName, "Equipment");
					//	omcRmuidRel.m_ObjectKey = Util.getCardObjectKey(omcRmuidRel.m_emsId,omcRmuidRel.m_ManagedElement,slotId,cardNumber+"");
					}



					logger.info("CRD file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("card", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Card data to DB start Failed !");
						return;
					}
					logger.info(" Process Card data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Card data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "Port".equalsIgnoreCase(type)) {
			try {
				if (prtFileNames.size() > 0) {
					logger.info(" Process Ptp data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(prtFileNames);
					for (OmcRmuidRel omcRmuidRel : omcRmuidRelList) {
						String m_ObjectTmfName = omcRmuidRel.m_ObjectTmfName;
						if (m_ObjectTmfName.indexOf("PTP")>0) {
							String ptpId = getInfo(m_ObjectTmfName, "PTP");
							//omcRmuidRel.m_ObjectKey=Util.getPtpObjectkey(omcRmuidRel.m_emsId,omcRmuidRel.m_ManagedElement,ptpId, "PTP");
						}else if (m_ObjectTmfName.indexOf("FTP")>0){
							String ptpId = getInfo(m_ObjectTmfName, "FTP");
						//	omcRmuidRel.m_ObjectKey=Util.getPtpObjectkey(omcRmuidRel.m_emsId,omcRmuidRel.m_ManagedElement,ptpId, "FTP");
						}
					}
					logger.info("PRT file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("ptp", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Ptp data to DB start Failed !");
						return;
					}
					logger.info(" Process Ptp data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Ptp data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "TopoLink".equalsIgnoreCase(type)) {
			try {
				if (tplFileNames.size() > 0) {
					logger.info(" Process TopoLink data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(tplFileNames);
					logger.info("TPL file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("topoLink", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process TopoLink data to DB start Failed !");
						return;
					}
					logger.info(" Process TopoLink data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process TopoLink data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "Tunnel".equalsIgnoreCase(type)) {
			try {
				if (tnlFileNames.size() > 0) {
					logger.info(" Process Tunnel data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(tnlFileNames);
					logger.info("TNL file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("tunnel", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Tunnel data to DB start Failed !");
						return;
					}
					logger.info(" Process Tunnel data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Tunnel data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "Pw".equalsIgnoreCase(type)) {
			try {
				if (pswFileNames.size() > 0) {
					logger.info(" Process Pw data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(pswFileNames);
					logger.info("PSW file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("pw", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Pw data to DB start Failed !");
						return;
					}
					logger.info(" Process Pw data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Pw data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		if (type == null || "Fdfr".equalsIgnoreCase(type)) {
			try {
				if (ethFileNames.size() > 0) {
					logger.info(" Process Fdfr data to DB start.. ");
					ArrayList<OmcRmuidRel> omcRmuidRelList = getCsvData(ethFileNames);
					logger.info("ETH file data count:" + omcRmuidRelList.size());
					omcRmuidRelList.addAll(getCsvData(tdmFileNames));
					logger.info("TDM file data count:" + omcRmuidRelList.size());
					omcRmuidRelList.addAll(getCsvData(l3iFileNames));
					logger.info("L3I file data count:" + omcRmuidRelList.size());
					if (omcRmuidRelList.size() == 0) {
						logger.error(" file no data, process Failed !");
						return;
					}
					boolean insertMeSuccess = insertData("fdfr", omcRmuidRelList);
					if (!insertMeSuccess) {
						logger.error(" Process Fdfr data to DB start Failed !");
						return;
					}
					logger.info(" Process Fdfr data to DB start Success !");
				}
			} catch (Exception e) {
				logger.error(" Process Fdfr data to DB start Exception !" + e.getMessage(), e);
				return;
			}
		}

		logger.info("Insert all data finished..");
	}

	private String getInfo(String m_ObjectTmfName,String type) {
		String value="";
		String[] split = m_ObjectTmfName.split(";");
		for (int i = 0; i < split.length; i++) {
			String strings=split[i];
			int index=strings.indexOf(type+"=");
			if (index>=0) {
				value = split[i].substring(strings.indexOf("=")+1);
			}
		}
		return value;
	}

	public ArrayList<OmcRmuidRel> getCsvData(List<String> fileNameList) {
		String record;
		ArrayList<OmcRmuidRel> omcRmuidRelList = new ArrayList<OmcRmuidRel>();
		try {
			for (String fileName : fileNameList) {
				// 设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
				BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

				int line = 0;
				// 遍历数据行并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
				while ((record = file.readLine()) != null) {
					String fields[] = null;
					if (record.contains(",")) {
						fields = record.split(",");
					} else {
						fields = record.split("\\|");
					}
					if (line == 0) {
						line++;
					} else {
						if (fields.length != 2) {
							logger.info("文件内容错误。退出解析！");
							break;
						}
						String objectTmfName = fields[0];
						String[] split = objectTmfName.split(";");
						String ems = "";
						String managedElement = "";
						for (int i = 0; i < split.length; i++) {
							if (split[i].contains("EMS")) {
								ems = split[i].split("=")[1];
							}
							if (split[i].contains("ManagedElement")) {
								managedElement = split[i].split("=")[1];
							}
						}
						String objectRmuid = fields[1];
						omcRmuidRelList.add(new OmcRmuidRel(objectTmfName, objectRmuid, ems, managedElement, null));
					}
				}
				// 关闭文件
				file.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return omcRmuidRelList;
	}

	private class OmcRmuidRel {
		public String m_ObjectTmfName;
		public String m_ObjectRmuid;
		public String m_emsId;
		public String m_ObjectKey;
		public String m_ManagedElement;

		public OmcRmuidRel(String objectTmfName, String objectRmuid, String emsId, String managedElement,
						   String objectKey) {
			m_ObjectTmfName = objectTmfName;
			m_ObjectRmuid = objectRmuid;
			m_emsId = emsId;
			m_ObjectKey = objectKey;
			m_ManagedElement = managedElement;

		}
		@Override
		public String toString() {
			return "OmcRmuidRel [m_ObjectTmfName=" + m_ObjectTmfName + ", m_ObjectRmuid=" + m_ObjectRmuid + ", m_emsId="
					+ m_emsId + ", m_ObjectKey=" + m_ObjectKey + ", m_ManagedElement=" + m_ManagedElement + "]";
		}
	}

	public boolean insertData(String tableName, ArrayList<OmcRmuidRel> omcRmuidRelList) {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		PreparedStatement delPstmt = null;
//		int currCount = 0;
//		try {
//			conn = getConnection();
//			if (conn == null) {
//				logger.error(" can not open db connection !");
//				return false;
//			}
//
//			//删除OMC新旧关系表数据
//			String delSql = "delete from " + tableName + "rmuidrel";
//			delPstmt = conn.prepareStatement(delSql);
//			delPstmt.execute();
//			delPstmt.close();
//
//			//插入OMC新旧关系表数据
//			conn.setAutoCommit(false);
//			int index = 1;
//
//			StringBuffer sqlBuf = new StringBuffer();
//			sqlBuf.append("insert into " + tableName + "rmuidrel(newrmuid,oldrmuid) values(?,?) ");
//
//			logger.info(" sql insert : " + sqlBuf.toString());
//
//			Map<String, String> keyMap = new HashMap<String, String>();
//			for(int i = 0; i < omcRmuidRelList.size(); i++) {
//				if(currCount == 0){
//					stmt = conn.prepareStatement(sqlBuf.toString());
//				}
//
//				OmcRmuidRel info = omcRmuidRelList.get(i);
//
//				String key=info.m_Newrmuid + "-" + info.m_Oldrmuid;
//				if(keyMap.containsKey(key)){
//					logger.info("Repeat data:" + key);
//					continue;
//				}else {
//					keyMap.put(key, info.m_Newrmuid);
//				}
//
//				index = 1;
//				stmt.setString(index++, info.m_Newrmuid);
//				stmt.setString(index++, info.m_Oldrmuid);
//				stmt.addBatch();
//
//				currCount = currCount + 1;
//
//				if(currCount == 1000 || i == omcRmuidRelList.size() - 1 ){
//					stmt.executeBatch();
//					stmt.close();
//					currCount = 0;
//				}
//			}
//			conn.commit();
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				logger.error(e1.toString());
//			}
//			logger.error(e.getMessage(), e);
//			return false;
//		} finally {
//			try {
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				logger.error(e.getMessage(), e);
//			}
//		}

		return true;
	}

	public static void main(String[] args) {
		String emsId = null;
		String dir = "D:\\工作资料\\运城北向映射表（corba-socket）";
		String type = null;

		try {
			Civil dataMigrationProcessor = new Civil();

			File directory =new File(dir);
			File [] files=directory.listFiles() ;

			List<String> list = new ArrayList<String>();
			for(File file: files)
			{
				list.add(file.getAbsolutePath());
			}

			String[] fileNames = list.toArray(new String[0]);
			dataMigrationProcessor.analyseData(fileNames, type);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}





}
