package nc7.javaproject.myapp.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import nc7.javaproject.vo.Participant;

public class ParticipantNetworkDao implements ParticipantDao {
  
  String dataName;
  DataInputStream in;
  DataOutputStream out;
  
  public ParticipantNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Participant participant) {
 
  }

  @Override
  public List<Participant> list() {

    return null;
  }

  @Override
  public Participant findBy(int no) {
    return null;
  }
 
  @Override
  public int update(Participant participant) {
    
    return 0;
  }

  @Override
  public int delete(int no) {
    
    return 0;
  }
}
