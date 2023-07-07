package nc7.javaproject.myapp.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.net.RequestEntity;
import nc7.net.ResponseEntity;

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
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/insert")
          .data(participant)
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Participant> list() {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/list")
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        throw new RuntimeException(response.getResult());
      }

      return response.getList(Participant.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Participant findBy(int no) {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/findBy")
          .data(no)
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return null;
      }

      return response.getObject(Participant.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Participant participant) {
    try {
      // 서버에 요청을 보낸다.
      out.writeUTF(new RequestEntity()
          .command(dataName + "/update")
          .data(participant)
          .toJson());

      // 서버에서 보낸 응답을 받는다.
      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return 0;
      }

      return response.getObject(Integer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/delete")
          .data(no)
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return 0;
      }

      return response.getObject(Integer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
