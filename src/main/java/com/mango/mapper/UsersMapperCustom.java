package com.mango.mapper;

import java.util.List;

import com.mango.pojo.Users;
import com.mango.pojo.vo.FriendRequestVO;
import com.mango.pojo.vo.MyFriendsVO;
import com.mango.utils.MyMapper;


/**
 * @author wjy
 */
public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}